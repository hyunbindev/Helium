package com.hyunbindev.userserevice.service.oauth2

import org.slf4j.LoggerFactory
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class Oauth2UserService : DefaultOAuth2UserService(){
    private val logger = LoggerFactory.getLogger(Oauth2UserService::class.java)
    override fun loadUser(request: OAuth2UserRequest): OAuth2User {
        val oAuth2User: OAuth2User = super.loadUser(request)
        return oAuth2User
    }
}