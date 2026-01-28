package com.hyunbindev.userserevice.service.wallet

import com.hyunbindev.userserevice.constant.wallet.PaymentType
import com.hyunbindev.userserevice.dto.wallet.PaymentEventDto
import com.hyunbindev.userserevice.entity.wallet.PaymentEntity
import com.hyunbindev.userserevice.repository.wallet.PaymentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository,
    private val walletService: WalletService
) {
    @Transactional
    fun paymentOnSuccess(event: PaymentEventDto) {
        val payment: PaymentEntity = paymentRepository.findById(event.paymentId)
            .orElseThrow{ RuntimeException("Payment with id ${event.paymentId} not found") }

        payment.success()
    }
    @Transactional
    fun paymentOnFailure(event: PaymentEventDto) {
        val payment: PaymentEntity = paymentRepository.findById(event.paymentId)
            .orElseThrow{ RuntimeException("Payment with id ${event.paymentId} not found") }

        val amount:Long = payment.amount

        when(payment.type){
            PaymentType.DEPOSIT -> payment.wallet.withdraw(amount)
            PaymentType.WITHDRAWAL -> payment.wallet.deposit(amount)
        }

        payment.fail()
    }
}