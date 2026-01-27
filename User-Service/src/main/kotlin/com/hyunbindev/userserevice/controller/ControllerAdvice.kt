package com.hyunbindev.userserevice.controller

import com.hyunbindev.userserevice.dto.auth.AuthExceptionResponse
import com.hyunbindev.userserevice.exception.AuthException
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class ControllerAdvice {
    /**
     * Auth 인증 관련 Exception Handler
     */
    @ExceptionHandler(AuthException::class)
    fun handleAuthException(e: AuthException, response: HttpServletResponse): ResponseEntity<AuthExceptionResponse> {

        //refresh token 삭제 필요시 삭제
        if(e.isDeleteRefreshToken) deleteRefreshTokenCookie(response)

        //exception에 따른 결과값 리턴
        return ResponseEntity.status(e.status).body(AuthExceptionResponse(
            code=e.code,
            message = e.userMessage,
            time = LocalDateTime.now(),
        ))
    }

    /**
     * cookie에 저장된 refresh token 삭제
     */
    private fun deleteRefreshTokenCookie(response: HttpServletResponse) {
        val cookie = Cookie("refresh_token", null).apply{
            isHttpOnly = true
            secure = true
            path= "/"
            maxAge = 0
        }
        response.addCookie(cookie)
    }
}