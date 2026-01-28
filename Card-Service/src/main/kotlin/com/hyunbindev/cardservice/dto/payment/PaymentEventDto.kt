package com.hyunbindev.cardservice.dto.payment

import com.fasterxml.jackson.annotation.JsonFormat
import com.hyunbindev.cardservice.constant.payment.PaymentType
import java.time.LocalDateTime
import java.util.UUID

data class PaymentEventDto(
    val paymentId: UUID,
    val userUuid: UUID,
    val amount:Long,
    val paymentType: PaymentType,
    val walletBalance:Long,
) {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    val timestamp:String = LocalDateTime.now().toString()
}