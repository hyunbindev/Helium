package com.hyunbindev.userserevice.exception

import com.hyunbindev.userserevice.constant.exception.ExceptionConstant
import org.springframework.http.HttpStatus

abstract class BaseException(
    exceptionConstant: ExceptionConstant,
    userMessage: String? = null
) : RuntimeException(exceptionConstant.getMessage()) {
    val status: HttpStatus = exceptionConstant.getStatus()
    val userMessage: String = userMessage ?: exceptionConstant.getUserMessage()
    val code: String = if (exceptionConstant is Enum<*>) exceptionConstant.name else "UNKNOWN"
}