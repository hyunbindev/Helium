package com.hyunbindev.cardservice.config.auth

import com.hyunbindev.common_auth_module.filter.HeaderAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfig {
    @Bean
    fun headerAuthenticationFilter(): HeaderAuthenticationFilter {
        return HeaderAuthenticationFilter()
    }
    @Bean
    fun filterChain(http: HttpSecurity, headerAuthenticationFilter: HeaderAuthenticationFilter): DefaultSecurityFilterChain {
        return http.csrf { it.disable() }
            .formLogin { it.disable() }
            .httpBasic { it.disable() }
            .authorizeHttpRequests { auth->auth.anyRequest().authenticated() }
            .addFilterBefore(headerAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }
}