package com.hyunbindev.userserevice.service.auth

import Oauth2Mapper
import UserOAuthInfo
import com.hyunbindev.common_auth_module.constant.OAuth2Provider
import com.hyunbindev.userserevice.entity.member.MemberEntity
import com.hyunbindev.userserevice.repository.member.MemberRepository
import com.hyunbindev.userserevice.service.member.SignupService
import com.hyunbindev.userserevice.service.member.UpdateService
import org.slf4j.LoggerFactory
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class OAuthLoginService(
    private val memberRepository: MemberRepository,
    private val updateService: UpdateService,
    private val signupService: SignupService,
    //Oauh2 mapper 구현체 리스트
    private  val mappers: List<Oauth2Mapper>
) {
    private val logger = LoggerFactory.getLogger(OAuthLoginService::class.java)
    //mappers 를 map으로 변환 관리
    private val mapperMap = mappers.associateBy { it.provider }

    /**
     * oauth2 인증, 및 인가
     */
    public fun sign(provider: OAuth2Provider, oAuth2User: OAuth2User): MemberEntity {
        val mapper: Oauth2Mapper? = mapperMap[provider]

        //지원 하지 않는 oauth2 provider 예외 처리
        if(mapper==null){
            logger.error("Unsupported OAuth2Provider: {}", provider)
            throw IllegalArgumentException("Unsupported OAuth2Provider: $provider")
        }

        val oauth2UserInfo: UserOAuthInfo = mapper.map(oAuth2User);

        val member: MemberEntity = memberRepository.findByProviderAndProviderId(oauth2UserInfo.provider,oauth2UserInfo.id)
            ?.also { update(it,oauth2UserInfo) }
            ?: run {signUp(oauth2UserInfo)}
        return member;
    }

    /**
     * OAuth2 사용자 정보 업데이트
     */
    private fun update(member: MemberEntity, oauth2UserInfo: UserOAuthInfo){
        updateService.updateByOauth(member,oauth2UserInfo)
    }

    /**
     * 신규 유저 회원 가입
     */
    private fun signUp(oauth2UserInfo: UserOAuthInfo): MemberEntity {
        return signupService.signupByOauth2(oauth2UserInfo)
    }
}