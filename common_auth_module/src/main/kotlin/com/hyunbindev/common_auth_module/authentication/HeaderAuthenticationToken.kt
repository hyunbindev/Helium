package com.hyunbindev.common_auth_module.authentication

import com.hyunbindev.common_auth_module.constant.OAuth2Provider
import org.springframework.security.authentication.AbstractAuthenticationToken
import java.util.UUID

class HeaderAuthenticationToken(
    private val userPrincipal: UserPrincipal?,
): AbstractAuthenticationToken(
    userPrincipal?.getAuthorities()?:emptyList()
) {
    @Deprecated(
        message = "헤더를 통한 인증인가 방식으로 인해 제거",
        level = DeprecationLevel.WARNING
    )
    override fun getCredentials(): Any? =null;

    init{
        if (userPrincipal != null) {super.setAuthenticated(true)}
    }
    override fun getName():String{
        return userPrincipal?.name ?: ""
    }

    fun getUuid(): UUID {
        return UUID.fromString(this.getName())
    }

    fun getProvider(): OAuth2Provider? {
        return userPrincipal?.getProvider();
    }

    override fun getPrincipal(): UserPrincipal? {
        return userPrincipal
    }
}