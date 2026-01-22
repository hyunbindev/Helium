package com.hyunbindev.apiserver.exception

import com.hyunbindev.apiserver.constant.exception.ExceptionConstant
import org.springframework.http.HttpStatus

abstract class BaseException(
    exceptionConstant: ExceptionConstant,
    userMessage: String? = null
) : RuntimeException(exceptionConstant.getMessage()) {
    val status: HttpStatus = exceptionConstant.getStatus()
    val userMessage: String = userMessage ?: exceptionConstant.getUserMessage()
}