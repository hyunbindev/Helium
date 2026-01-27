package com.hyunbindev.cardservice.exception

import com.hyunbindev.common_exception_module.BaseException
import com.hyunbindev.common_exception_module.ExceptionConstant

class CardException(exceptionConstant: ExceptionConstant, userMessage: String? = null):BaseException(exceptionConstant, userMessage) {
}