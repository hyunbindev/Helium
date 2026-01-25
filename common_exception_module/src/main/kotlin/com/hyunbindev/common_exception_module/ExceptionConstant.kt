package com.hyunbindev.common_exception_module

import org.springframework.http.HttpStatus

/**
 * ### 전역 예외 규격
 * 모든 도메인 예외 상수는 이 interface의 enum 구현체 여야 합니다
 * @author hyunbindev
 * @since 2026-01-26
 */
interface ExceptionConstant {
    fun getMessage(): String
    fun getStatus(): HttpStatus
    fun getUserMessage(): String
}