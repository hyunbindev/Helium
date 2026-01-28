package com.hyunbindev.userserevice.dto.wallet

import com.hyunbindev.userserevice.constant.wallet.PaymentType
import java.time.LocalDateTime
import java.util.UUID

data class PaymentEventDto(
    val paymentId: UUID,
    val userUuid: UUID,
    val amount:Long,
    val paymentType: PaymentType,
    val walletBalance:Long,
) {
    val timestamp:String = LocalDateTime.now().toString()
}