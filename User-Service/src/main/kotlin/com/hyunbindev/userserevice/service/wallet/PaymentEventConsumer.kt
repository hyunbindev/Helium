package com.hyunbindev.userserevice.service.wallet

import com.hyunbindev.userserevice.dto.wallet.PaymentEventDto
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class PaymentEventConsumer(
    private val paymentService: PaymentService
) {
    @KafkaListener(topics = ["payment-success-events"], groupId = "payment-group")
    fun consumePaymentSuccess(event: PaymentEventDto){
        paymentService.paymentOnSuccess(event)
    }
    @KafkaListener(topics = ["payment-failure-events"], groupId = "payment-group")
    fun consumePaymentFailure(event: PaymentEventDto){
        paymentService.paymentOnFailure(event)
    }
}