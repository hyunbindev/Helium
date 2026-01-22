package com.hyunbindev.userserevice.service.auth

import com.hyunbindev.userserevice.security.jwt.JwtProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class TokenService(
    private val stringRedisTemplate: StringRedisTemplate,
    @Value($$"${jwt.access.expire}") private val refreshExpireIn:Long
) {
    public fun saveRefreshToken(userId:String ,refreshToken: String) {
        stringRedisTemplate.opsForValue().set(
            "RT: $userId",
            refreshToken,
            refreshExpireIn,
            TimeUnit.SECONDS)
    }

    public fun getRefreshToken(userId:String):String? = stringRedisTemplate.opsForValue().get("RT: $userId")

    public fun deleteRefreshToken(userId:String) = stringRedisTemplate.delete("RT: $userId")
}