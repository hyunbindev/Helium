package com.hyunbindev.userserevice.security.jwt

import com.hyunbindev.userserevice.entity.member.MemberEntity
import com.nimbusds.jwt.JWT
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.oauth2.jwt.Jwt


import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.security.Key
import java.util.Date
import java.util.UUID

@Component
class JwtProvider(
    @Value($$"${jwt.access.key}") private val ACCESS_KEY: String,
    @Value($$"${jwt.access.expire}") private val ACCESS_EXPIRES_IN: Long,
) {
    private val accessKey: Key = Keys.hmacShaKeyFor(ACCESS_KEY.toByteArray(StandardCharsets.UTF_8))

    /**
     * jwt access token 발급
     * @author hyunbindev
     * @since 2026-01-17
     */
    public fun createAccessToken(member: MemberEntity):String{
        val now = Date();
        val claims: Claims = Jwts.claims().setSubject(member.id.toString())
        //권한
        claims["roles"] = member.authority.value
        //Oauth2 provider
        claims["provider"] = member.provider.providerName
        claims["authority"] = member.authority.value
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + ACCESS_EXPIRES_IN))
            .signWith(accessKey)
            .compact()
    }

    /**
     * refresh token 발급
     * @author hyunbindev
     * @since 2026-01-17
     */
    public fun createRefreshToken(memberId:String):String{
        val refreshToken = UUID.randomUUID().toString()

        return refreshToken;
    }
}