package com.hyunbindev.userserevice.service.member

import UserOAuthInfo
import com.hyunbindev.common_auth_module.constant.Role
import com.hyunbindev.userserevice.entity.member.MemberEntity
import com.hyunbindev.userserevice.repository.member.MemberRepository
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
            authority = Role.MEMBER
        )

        logger.info("Signup User OAuth Info {}:" ,userOAuthInfo)

        return memberRepository.save(member)
    }
}