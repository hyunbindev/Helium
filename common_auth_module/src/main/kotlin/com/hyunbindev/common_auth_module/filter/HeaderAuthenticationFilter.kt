package com.hyunbindev.common_auth_module.filter


import com.hyunbindev.common_auth_module.authentication.HeaderAuthenticationToken
import com.hyunbindev.common_auth_module.authentication.UserPrincipal
import com.hyunbindev.common_auth_module.constant.OAuth2Provider
import com.hyunbindev.common_auth_module.constant.Role
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.AntPathMatcher
import org.springframework.web.filter.OncePerRequestFilter

/**
 * Header 값 추출 및 Spring Security Context
 * - @author hyunbindev
 * - @since 2026-01-19
 *
 * - @since 2026-01-21
 * OAuth2, 인증 및 redirection 시 filter 제외
 */
class HeaderAuthenticationFilter: OncePerRequestFilter() {
    private val log: Logger = LoggerFactory.getLogger(HeaderAuthenticationFilter::class.java)
    private val pathMatcher: AntPathMatcher=AntPathMatcher()
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val xUserId:String? = request.getHeader("X-User-Id")

        val authorityHeader = request.getHeader("X-User-Authority")

        val providerHeader = request.getHeader("X-User-Provider")

        val authority:Role = Role.entries.find{ it.value == authorityHeader }?: Role.GUEST

        val provider: OAuth2Provider? = OAuth2Provider.entries.find{ it.name == providerHeader || it.providerName == providerHeader }

        val authorities:Collection<GrantedAuthority> = listOf(SimpleGrantedAuthority(authority.name))

        val authentication: Authentication = HeaderAuthenticationToken(
            UserPrincipal(
                xUserHeaderId = xUserId,
                provider = provider,
                authorities
            ),
        )
        SecurityContextHolder.getContext().authentication = authentication

        filterChain.doFilter(request, response)
    }

    /**
     * OAuth2 인증시 filter 제외
     */
    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        val path = request.requestURI
        return pathMatcher.match("/oauth2/**",path) || pathMatcher.match("/login/**",path)
    }
}