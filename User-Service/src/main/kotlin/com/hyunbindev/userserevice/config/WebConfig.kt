package com.hyunbindev.userserevice.config

import com.hyunbindev.common_auth_module.annotation.UserUuidArgumentResolver
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 *# common auth UUID resolver
 *  * 모든 서비스는 use식별시 AUTHENTICATION UUID 파라미터를 받아 수행
 * @param userUuid UUID
 */

@Configuration
class WebConfig(
    private val userUuidArgumentResolver: UserUuidArgumentResolver
): WebMvcConfigurer {
    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(userUuidArgumentResolver)
    }
}