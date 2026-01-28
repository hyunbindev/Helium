package com.hyunbindev.userserevice.constant.exception

import com.hyunbindev.common_exception_module.ExceptionConstant
import org.springframework.http.HttpStatus

enum class PaymentExceptionConst(
    private val message: String,
    private val status: HttpStatus,
    private val userDefaultMessage: String
):ExceptionConstant {
    ALREADY_PROCESSED_PAYMENT("This payment has already been processed!", HttpStatus.CONFLICT, "이미 처리 완료된 결제 건입니다.");
    override fun getMessage(): String = message

    override fun getStatus(): HttpStatus = status

    override fun getUserMessage(): String = userDefaultMessage
}