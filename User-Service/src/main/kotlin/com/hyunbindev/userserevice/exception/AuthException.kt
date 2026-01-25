package com.hyunbindev.userserevice.exception

import com.hyunbindev.common_exception_module.BaseException
import com.hyunbindev.common_exception_module.ExceptionConstant

class AuthException(exceptionConstant: ExceptionConstant, userMessage:String? = null, val isDeleteRefreshToken:Boolean=false): BaseException(exceptionConstant, userMessage) {

}