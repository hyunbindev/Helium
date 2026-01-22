package com.hyunbindev.userserevice.service.member

import UserOAuthInfo
import com.hyunbindev.userserevice.entity.member.MemberEntity
import com.hyunbindev.userserevice.repository.member.MemberRepository
import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class UpdateService(
    private val memberRepository: MemberRepository,
) {
    private val logger = LoggerFactory.getLogger(UpdateService::class.java)

    @Transactional
    public fun updateByOauth(member:MemberEntity, userOAuthInfo: UserOAuthInfo){
        var isUpdated:Boolean = false

        if(userOAuthInfo.profileImageUrl!=member.profileImageUrl) isUpdated = true

        if(isUpdated) memberRepository.save(member)
    }
}