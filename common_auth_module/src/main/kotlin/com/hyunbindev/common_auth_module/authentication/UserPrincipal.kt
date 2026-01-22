package com.hyunbindev.userserevice.security.authentication

import com.hyunbindev.userserevice.constant.member.Role
import com.hyunbindev.userserevice.constant.oauth.OAuth2Provider
import org.springframework.security.core.AuthenticatedPrincipal
import org.springframework.security.core.GrantedAuthority

class UserPrincipal(
    private val xUserHeaderId:String?,
    private val provider: OAuth2Provider?,
    private val authorities: Collection<GrantedAuthority>?,
): AuthenticatedPrincipal {

    override fun getName(): String {
        return xUserHeaderId?:"Guest"
    }

    fun getProvider(): OAuth2Provider? {
        return provider
    }

    fun getAuthorities(): Collection<GrantedAuthority> {
        return authorities?:listOf(Role.GUEST)
    }
}