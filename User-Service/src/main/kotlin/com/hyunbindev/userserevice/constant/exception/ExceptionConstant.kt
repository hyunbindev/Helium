package com.hyunbindev.apiserver.constant.exception

import org.springframework.http.HttpStatus

interface ExceptionConstant {
    fun getMessage(): String
    fun getStatus(): HttpStatus
    fun getUserMessage(): String
}