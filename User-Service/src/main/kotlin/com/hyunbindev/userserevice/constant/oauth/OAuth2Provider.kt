package com.hyunbindev.apiserver.constant.oauth

import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User

/**
 * OAuth2 provider ENUM
 * @author hyunbindev
 * @since 2025-01-16
 * 다중 OAuth2 로그인을 위한 ENUM
 */
enum class OAuth2Provider(val providerName: String, attributeKey:String) {
    KAKAO("KAKAO","id"),
    GOOGLE("GOOGLE","sub"),
    APPLE("APPLE","sub");

    companion object {
        /**
         * OAUTH2 요청시 Provider return
         * 미지원 요청시 throw IllegalArgumentException
         * @return OAuth2Provider, IllegalArgumentException
         */
        fun from(request: OAuth2UserRequest):OAuth2Provider{
            return returnProvider(request.clientRegistration.registrationId)
        }

        fun from(authentication: Authentication):OAuth2Provider{
            val token = authentication as OAuth2AuthenticationToken
            return returnProvider(token.authorizedClientRegistrationId)
        }

        private fun returnProvider(registrationId:String):OAuth2Provider{
            return when(registrationId.lowercase()){
                "google" -> GOOGLE
                "apple" -> APPLE
                "kakao" -> KAKAO
                else -> throw IllegalArgumentException("Unknown OAuth2Provider")
            }
        }
    }
}