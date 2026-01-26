package com.hyunbindev.cardservice.config

import com.hyunbindev.common_auth_module.authentication.HeaderAuthenticationToken
import feign.RequestInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

@Configuration
class FeignConfig {
    @Bean
    fun requestInterceptor(): RequestInterceptor{
        //request 공통 헤더 설정
        return RequestInterceptor{
            template ->
            val authentication: HeaderAuthenticationToken = SecurityContextHolder.getContext().authentication as HeaderAuthenticationToken
            template.header("X-User-Id", authentication.name)
            template.header("X-User-Authority", authentication.authorities.firstOrNull()?.authority ?: "ROLE_ANONYMOUS")
            template.header("X-User-Provider",authentication.getProvider()?.providerName)
        }
    }
}