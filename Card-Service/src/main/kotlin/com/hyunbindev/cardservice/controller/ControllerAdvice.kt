package com.hyunbindev.cardservice.controller

import com.hyunbindev.cardservice.exception.CardException
import com.hyunbindev.cardservice.exception.FeignClientException
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class ControllerAdvice {
    /**
     * card exception 전역 핸들러
     */
    @ExceptionHandler(CardException::class)
    fun handleCardException(e: CardException, response: HttpServletResponse): ResponseEntity<Map<String, Any?>> {

        val errorBody = mapOf(
            "code" to e.code,
            "message" to e.userMessage,
            "time" to LocalDateTime.now()
        )
        //exception에 따른 결과값 리턴
        return ResponseEntity.status(e.status).body(errorBody)
    }
    @ExceptionHandler(FeignClientException::class)
    fun handleFeignException(e: FeignClientException, response: HttpServletResponse): ResponseEntity<Map<String, Any?>> {
        val errorBody = mapOf(
            "code" to e.code,
            "message" to e.message,
            "time" to e.timestamp
        )
        return ResponseEntity.status(e.status).body(errorBody)
    }

}