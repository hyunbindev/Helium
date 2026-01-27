package com.hyunbindev.cardservice.event.payment.handler

import com.hyunbindev.cardservice.event.payment.event.PaymentEvent
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

/**
 * ### 재화 결제 서비스 에서 transaction 성공, 실패 이벤트 핸들러
 * @author hyunbindev
 */
@Component
class PaymentEventListener {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun handleSuccess(event: PaymentEvent) {

    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    fun handleFail(event: PaymentEvent) {

    }
}