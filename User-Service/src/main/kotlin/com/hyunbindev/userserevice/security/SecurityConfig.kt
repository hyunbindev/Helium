package com.hyunbindev.userserevice.security

import com.hyunbindev.common_auth_module.filter.HeaderAuthenticationFilter
import com.hyunbindev.userserevice.service.auth.oauth2.Oauth2FailHandlerService
import com.hyunbindev.userserevice.service.auth.oauth2.Oauth2SuccessHandlerService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

/**
 * Spring security Configuration
 * @author hyunbindev
 * @since 2026-01-15
 */
@Configuration
class SecurityConfig(
    private val oauth2FailHandlerService: Oauth2FailHandlerService,
    private val oauth2SuccessHandlerService: Oauth2SuccessHandlerService,
) {
    @Bean
    fun headerAuthenticationFilter(): HeaderAuthenticationFilter {
        return HeaderAuthenticationFilter()
    }
    @Bean
    fun filterChain(http: HttpSecurity,headerAuthenticationFilter:HeaderAuthenticationFilter): DefaultSecurityFilterChain {
        return http
            //csrf 비활성화
            .csrf { it.disable() }
            //rest api form login 비활성화
            .formLogin { it.disable() }
            //기본 인증 비활성화
            .httpBasic { it.disable() }
            //jwt token 사용 stateless 인증 방식
            //.sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests {
                //허용 url
                auth-> auth.requestMatchers("/api/v1/public","/api/v1/login/**","/api/v1/test**").permitAll()
                .anyRequest()
                .authenticated()
            }
            .oauth2Login {
                oauth2-> oauth2
                    .successHandler (oauth2SuccessHandlerService)
                    .failureHandler (oauth2FailHandlerService)
            }
            .exceptionHandling {
                it.authenticationEntryPoint { _, response, _ ->response.sendError(HttpServletResponse.SC_UNAUTHORIZED)}
                it.accessDeniedHandler { _, response, _ ->  response.sendError(HttpServletResponse.SC_FORBIDDEN)}
            }
            .addFilterBefore(headerAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }
}