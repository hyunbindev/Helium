package com.hyunbindev.gateway.filter

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.net.http.HttpHeaders
import java.nio.charset.StandardCharsets

@Component
class JwtAuthFilter(
    @Value("\${jwt.access.key}") private val ACCESS_KEY: String
): GlobalFilter {
    private val logger = LoggerFactory.getLogger(JwtAuthFilter::class.java)

    private val whiteListPaths = listOf("/api/v1/test", "/oauth2/","/login/")

    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
        val requestPath = exchange.request.path.toString()

        //white list jwt filter 우회
        if(whiteListPaths.any { requestPath.startsWith(it) }) return chain.filter(exchange)

        //header 로 부터 access token 추출
        val accessToken: String = exchange.request.headers.getFirst("Authorization")
            ?: return onError(exchange,"Access token not set",HttpStatus.UNAUTHORIZED)

        //jwt token 파싱 및 필터 진행
        return try{
            //키로 부터 key 파싱
            val key = Keys.hmacShaKeyFor(ACCESS_KEY.toByteArray(StandardCharsets.UTF_8))

            //claim 추출
            val claims: Claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(accessToken)
                .body

            //아이디, 권한, oauth2 제공자
            val userId = claims.subject
            val authority = claims["roles"].toString()
            val provider = claims["provider"].toString()

            //헤더 변환
            val mutatedRequest = exchange.request.mutate()
                .header("X-User-Id",userId)
                .header("X-User-Authority",authority)
                .header("X-User-Provider",provider)
                .build()

            //요청 정보 로그
            logger.info("Request :: path : [{}] id : [{}] authority: [{}] provider : [{}]",requestPath,userId,authority,provider)

            //필터 진행
            chain.filter(exchange.mutate().request(mutatedRequest).build())
        }catch(e: Exception){
            //필터 실패시
            logger.error("Error while requesting access token", e)
            onError(exchange, "Invalid Access Token", HttpStatus.UNAUTHORIZED)
        }
    }

    private fun onError(exchange: ServerWebExchange, err:String, status: HttpStatus):Mono<Void>{
        val response = exchange.response
        response.statusCode = status
        return response.setComplete()
    }
}