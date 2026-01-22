package com.hyunbindev.userserevice.service.oauth2.Oauth2Mapper

import com.hyunbindev.userserevice.constant.oauth.OAuth2Provider
import org.springframework.security.oauth2.core.user.OAuth2User

/**
 * oauth2 user Info mapping interface
 * @author hyunbindev
 * @since 2026-01-16
 *
 * oauth2 provider 확장성을 위한 oauth2Mapper
 */
interface Oauth2Mapper {
    val provider: OAuth2Provider
    fun map(oAuth2User: OAuth2User):UserOAuthInfo
}

/**
 * oauh2 user info data class
 * @author hyunbindev
 * @since 2026-01-16
 */
data class UserOAuthInfo(

    val provider: OAuth2Provider,
    val id: String,
    val nickname: String,
    val profileImageUrl: String,
)