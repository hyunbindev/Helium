package com.hyunbindev.userserevice.dto.auth

import java.time.LocalDateTime


/**
 * Auth 예외처리 DTO
 */
data class AuthExceptionResponse(
    val code: String,
    val message: String,
    val time: LocalDateTime,
)