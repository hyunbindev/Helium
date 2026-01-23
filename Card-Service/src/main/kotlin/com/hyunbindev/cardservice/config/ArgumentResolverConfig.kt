package com.hyunbindev.cardservice.config

import com.hyunbindev.common_auth_module.annotation.UserUuidArgumentResolver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ArgumentResolverConfig {
    @Bean
    fun userUuidArgumentResolver() = UserUuidArgumentResolver()
}