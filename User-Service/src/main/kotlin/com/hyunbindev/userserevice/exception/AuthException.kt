package com.hyunbindev.userserevice.exception

import com.hyunbindev.userserevice.constant.exception.ExceptionConstant

class AuthException(exceptionConstant: ExceptionConstant, userMessage:String? = null, val isDeleteRefreshToken:Boolean=false): BaseException(exceptionConstant, userMessage) {

}