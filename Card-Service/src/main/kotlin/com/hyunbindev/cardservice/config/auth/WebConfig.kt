package com.hyunbindev.cardservice.config.auth

import com.hyunbindev.common_auth_module.annotation.UserUuidArgumentResolver
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver

/**
 * common auth uuid resolver 등록
 */
@Configuration
class WebConfig(
    private val userUuidArgumentResolver: UserUuidArgumentResolver
): WebMvcConfigurer {
    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(userUuidArgumentResolver)
    }
}