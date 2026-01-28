package com.hyunbindev.userserevice.entity.wallet

import com.hyunbindev.userserevice.constant.exception.PaymentExceptionConst
import com.hyunbindev.userserevice.constant.wallet.PaymentStatus
import com.hyunbindev.userserevice.constant.wallet.PaymentType
import com.hyunbindev.userserevice.exception.PaymentException
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime
import java.util.UUID
/**
 * 지갑 내 자산의 입금 및 출금 이력을 관리하는 엔티티.
 *
 * 이 테이블은 모든 자금 흐름의 '원천 장부' 역할을 하며, 다음과 같은 특징을 가집니다:
 * - 멱등성: 외부 결제사 ID([external_tx_id]) 및 [id]를 통해 중복 처리를 방지함.
 * - 상태 제어: [PaymentStatus.PENDING] 상태로 생성된 후, 워커 또는 외부 알림에 의해
 * 최종 확정([PaymentStatus.SUCCESS], [PaymentStatus.FAIL])됨.
 * - 만료 처리: 일정 시간 이상 PENDING 상태인 기록은 시스템 스케줄러에 의해 자동 실패 처리될 수 있음.
 *
 * @property wallet 이력과 연결된 유저 지갑 엔티티
 * @property status 현재 결제/입금 진행 상태
 * @property type 거래 유형 (입금 또는 출금)
 * @property amount 거래 금액
 * @property processedAt 최종 상태(SUCCESS/FAIL)가 결정된 시각
 */
@Entity
class PaymentEntity(
    @ManyToOne(fetch = FetchType.LAZY)
    val wallet: WalletEntity,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,updatable=false)
    val type: PaymentType,

    @Column(nullable = false,updatable=false)
    val amount: Long,

) {
    @Id
    @Column(updatable=false, nullable = false, columnDefinition = "BINARY(16)")
    val id : UUID = UUID.randomUUID()

    @Column(updatable=false, nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()

    //최종 결제 성공 및 실패 시간
    @Column
    var processedAt: LocalDateTime? = null

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: PaymentStatus= PaymentStatus.PENDING

    fun success(){
        if(status != PaymentStatus.PENDING){
            throw PaymentException(
                paymentId = id,
                userUuid = wallet.memberId,
                paymentType = type,
                exceptionConstant = PaymentExceptionConst.ALREADY_PROCESSED_PAYMENT,
            )
        }
        status = PaymentStatus.SUCCESS
        processedAt = LocalDateTime.now()
    }

    fun fail(){
        if(status != PaymentStatus.PENDING){
            throw PaymentException(
                paymentId = id,
                userUuid = wallet.memberId,
                paymentType = type,
                exceptionConstant = PaymentExceptionConst.ALREADY_PROCESSED_PAYMENT,
            )
        }
        status = PaymentStatus.FAILURE
        processedAt = LocalDateTime.now()
    }
}