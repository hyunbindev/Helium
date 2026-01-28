package com.hyunbindev.userserevice.exception

import com.hyunbindev.common_exception_module.BaseException
import com.hyunbindev.common_exception_module.ExceptionConstant
import com.hyunbindev.userserevice.constant.wallet.PaymentType
import java.util.UUID

class PaymentException(
    val paymentId: UUID,
    val userUuid: UUID,
    val paymentType: PaymentType,
    exceptionConstant: ExceptionConstant,
    userMessage: String? = null):
    BaseException(exceptionConstant, userMessage) {
}