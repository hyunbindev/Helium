package com.hyunbindev.userserevice.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/test")
class TestController(
    @Value($$"${jwt.access.key}") private val accessKey:String,
    @Value($$"${spring.security.oauth2.client.registration.kakao.client-id}") private val clientId:String,
    @Value($$"${spring.data.redis.password}") private val redisPw:String
) {
    @PreAuthorize("permitAll()")
    @GetMapping
    fun test(): Map<String,String> =  mapOf("accessKey" to accessKey,"clientId" to clientId, "redisPw" to redisPw)
}