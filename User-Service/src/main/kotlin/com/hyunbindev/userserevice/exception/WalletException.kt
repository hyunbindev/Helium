package com.hyunbindev.userserevice.exception

import com.hyunbindev.common_exception_module.BaseException
import com.hyunbindev.common_exception_module.ExceptionConstant

class WalletException(
    exceptionConstant: ExceptionConstant,
    userMessage: String? = null
): BaseException(exceptionConstant , userMessage) {
}