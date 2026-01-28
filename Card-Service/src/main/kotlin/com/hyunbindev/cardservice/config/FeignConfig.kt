package com.hyunbindev.cardservice.config
import com.hyunbindev.cardservice.exception.FeignClientException
import com.hyunbindev.common_auth_module.authentication.HeaderAuthenticationToken
import feign.RequestInterceptor
import feign.codec.ErrorDecoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import tools.jackson.databind.ObjectMapper

@Configuration
class FeignConfig(
    private val objectMapper: ObjectMapper
) {
    @Bean
    fun requestInterceptor(): RequestInterceptor{
        //request 공통 헤더 설정
        return RequestInterceptor{
            template ->
            val authentication: HeaderAuthenticationToken = SecurityContextHolder.getContext().authentication as HeaderAuthenticationToken
            template.header("X-User-Id", authentication.name)
            template.header("X-User-Authority", authentication.authorities.firstOrNull()?.authority ?: "ROLE_ANONYMOUS")
            template.header("X-User-Provider",authentication.getProvider()?.providerName)
        }
    }
    @Bean
    fun exceptionDecoder(): ErrorDecoder {
        return ErrorDecoder{
            methodKey, response ->
            val body = response.body()?.asInputStream()?.use {
                objectMapper.readTree(it)
            }
            val errorCode = body?.get("code")?.asText() ?: "EXTERNAL_SERVER_ERROR"
            val errorMessage = body?.get("message")?.asText() ?: "외부 서비스 호출 중 알 수 없는 오류가 발생했습니다."
            val timestamp = body?.get("timestamp")?.asText()?: "unknown time stamp"
            FeignClientException(
                status = HttpStatus.valueOf(response.status()),
                code = errorCode,
                message = errorMessage,
                timestamp = timestamp,
            )
        }
    }
}