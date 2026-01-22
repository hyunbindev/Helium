package com.hyunbindev.apiserver.service.member

import com.hyunbindev.apiserver.entity.member.MemberEntity
import com.hyunbindev.apiserver.repository.member.MemberRepository
import com.hyunbindev.apiserver.service.oauth2.Oauth2Mapper.UserOAuthInfo
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * signup Service
 * @author hyunbindev
 * @since 2026-01-16
 */
@Service
class SignupService(
    private val memberRepository: MemberRepository
) {
    private val logger = LoggerFactory.getLogger(SignupService::class.java)

    /**
     * oauth 활용 회원가입 로직
     * @author hyunbindev
     * @since 2026-01-16
     */
    @Transactional
    fun signupByOauth2(userOAuthInfo:UserOAuthInfo):MemberEntity{

        val member:MemberEntity = MemberEntity(
            provider = userOAuthInfo.provider,
            providerId = userOAuthInfo.id,
            nickname = userOAuthInfo.nickname,
            profileImageUrl = userOAuthInfo.profileImageUrl,
        )

        logger.info("Signup User OAuth Info {}:" ,userOAuthInfo)

        return memberRepository.save(member)
    }
}