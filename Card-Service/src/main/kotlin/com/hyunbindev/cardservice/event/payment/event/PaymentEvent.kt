package com.hyunbindev.cardservice.event.payment.event

import java.util.UUID

sealed class PaymentEvent(
    val userUuid: UUID,
    val amount:Long,
    val type: PaymentType
) {
    class Deposit(
        userUuid: UUID,
        amount:Long,
    ): PaymentEvent(userUuid, amount, PaymentType.DEPOSIT)
    class Withdrawal(
        userUuid: UUID,
        amount:Long,
    ): PaymentEvent(userUuid, amount, PaymentType.WITHDRAWAL)
}