package com.hyunbindev.userserevice.service.auth.oauth2.Oauth2Mapper

import Oauth2Mapper
import UserOAuthInfo
import com.hyunbindev.common_auth_module.constant.OAuth2Provider
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Component
import kotlin.collections.get

/**
 * OAuth2 provider kakao 구현체
 * @author hyunbindev
 * @since 2026-01-16
 */
@Component
class Oauth2KakaoMapper: Oauth2Mapper {
    override val provider: OAuth2Provider = OAuth2Provider.KAKAO

    override fun map(oAuth2User: OAuth2User): UserOAuthInfo {
        val attributes = oAuth2User.attributes
        val properties = attributes["properties"] as Map<*, *>
        return UserOAuthInfo(
            provider,
            attributes["id"].toString(),
            properties["nickname"].toString(),
            properties["thumbnail_image"].toString(),
        )
    }
}