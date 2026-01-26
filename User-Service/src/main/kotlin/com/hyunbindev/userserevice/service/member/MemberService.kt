package com.hyunbindev.userserevice.service.member

import com.hyunbindev.userserevice.constant.exception.MemberExceptionConst
import com.hyunbindev.userserevice.dto.member.MemberInfoResponse
import com.hyunbindev.userserevice.entity.member.MemberEntity
import com.hyunbindev.userserevice.exception.MemberException
import com.hyunbindev.userserevice.repository.member.MemberRepository
import io.lettuce.core.KillArgs.Builder.id
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class MemberService(
    private val memberRepository: MemberRepository,
) {
    @Transactional(readOnly = true)
    public fun getMember(userUuid:UUID):MemberInfoResponse{
        val member:MemberEntity = memberRepository.findById(userUuid).orElseThrow{MemberException(MemberExceptionConst.MEMBER_NOT_FOUND)}
        return MemberInfoResponse(
            id = member.id,
            nickName = member.nickname,
            profileImageUrl = member.profileImageUrl,
        )
    }

    @Transactional(readOnly = true)
    public fun getMembers(userIds:List<String>):List<MemberInfoResponse>{
        val userUuids:List<UUID> = userIds.map { UUID.fromString(it) }
        val members:List<MemberEntity> = memberRepository.findAllById(userUuids)
        return  members.map{ member->
            MemberInfoResponse(
                id = member.id,
                nickName = member.nickname,
                profileImageUrl = member.profileImageUrl,
            )
        }
    }
}