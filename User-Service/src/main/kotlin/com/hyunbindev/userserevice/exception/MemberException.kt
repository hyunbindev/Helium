package com.hyunbindev.userserevice.exception

import com.hyunbindev.userserevice.constant.exception.ExceptionConstant

class MemberException(exceptionConstant: ExceptionConstant, userMessage: String? = null) :
    BaseException(exceptionConstant, userMessage) {
}