package com.hyunbindev.userserevice.constant.exception

import com.hyunbindev.common_exception_module.ExceptionConstant
import org.springframework.http.HttpStatus

enum class WalletExceptionConst(
    private val message: String,
    private val status: HttpStatus,
    private val userDefaultMessage: String
): ExceptionConstant {
    ILLEGAL_ARGUMENT("Illegal Argument exception",HttpStatus.BAD_REQUEST,"잘못된 요청입니다."),
    INSUFFICIENT_BALANCE("Insufficient user wallet balance", HttpStatus.UNPROCESSABLE_ENTITY,"잔액이 부족합니다.");
    override fun getMessage(): String = message

    override fun getStatus(): HttpStatus = status

    override fun getUserMessage(): String = userDefaultMessage
}