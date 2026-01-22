package com.hyunbindev.common_auth_module.filter

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * 공통 header filter 등록
 * @author hyunbindev
 * @since 2026-01-22
 */
@Configuration
class CommonAuthSecurityConfig {
    @Bean
    fun headerAuthenticationFilter(): HeaderAuthenticationFilter {
        return HeaderAuthenticationFilter()
    }
}