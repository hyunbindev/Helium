package com.hyunbindev.apiserver.service.member

import com.hyunbindev.apiserver.constant.exception.MemberExceptionConst
import com.hyunbindev.apiserver.entity.member.MemberEntity
import com.hyunbindev.apiserver.exception.MemberException
import com.hyunbindev.apiserver.repository.member.MemberRepository
import com.hyunbindev.apiserver.service.oauth2.Oauth2Mapper.UserOAuthInfo
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