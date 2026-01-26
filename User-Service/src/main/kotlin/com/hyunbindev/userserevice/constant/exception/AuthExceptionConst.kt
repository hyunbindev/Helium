package com.hyunbindev.userserevice.constant.exception

import com.hyunbindev.common_exception_module.ExceptionConstant
import org.springframework.http.HttpStatus

enum class AuthExceptionConst(
    private val message: String,
    private val status: HttpStatus,
    private val userDefaultMessage: String
): ExceptionConstant {
    ACCESS_TOKEN_EXPIRE("Access token is expired", HttpStatus.UNAUTHORIZED,"Access token 만료"),
    REFRESH_TOKEN_EXPIRE("Refresh token is expired", HttpStatus.UNAUTHORIZED,"Refresh token 만료"),
    INVALID_AUTH_REQUEST("Invalid auth request", HttpStatus.BAD_REQUEST,"잘못된 사용자 요청 입니다."),
    INVALID_REFRESH_TOKEN("Invalid refresh token", HttpStatus.UNAUTHORIZED,"잘못된 사용자 인증 정보 입니다."),
    REFRESH_TOKEN_NOT_FOUND("Not found refresh token,",HttpStatus.UNAUTHORIZED,"사용자 정보가 없습니다. 다시 로그인 해주세요."),
    INTERAL_SERVER_ERROR("Interal server error",HttpStatus.INTERNAL_SERVER_ERROR,"사용자 인증 실패 다시 시도해 주세요.");

    override fun getMessage(): String = message
    override fun getStatus(): HttpStatus = status
    override fun getUserMessage(): String = userDefaultMessage
}