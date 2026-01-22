package com.hyunbindev.apiserver.exception

import com.hyunbindev.apiserver.constant.exception.ExceptionConstant

class MemberException(exceptionConstant: ExceptionConstant, userMessage: String? = null) :
    BaseException(exceptionConstant, userMessage) {
}