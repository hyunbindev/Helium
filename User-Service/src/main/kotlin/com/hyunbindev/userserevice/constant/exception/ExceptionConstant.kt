package com.hyunbindev.userserevice.constant.exception

import org.springframework.http.HttpStatus

interface ExceptionConstant {
    fun getMessage(): String
    fun getStatus(): HttpStatus
    fun getUserMessage(): String
}