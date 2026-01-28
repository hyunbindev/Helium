package com.hyunbindev.userserevice.service.wallet

import com.hyunbindev.userserevice.constant.exception.WalletExceptionConst
import com.hyunbindev.userserevice.constant.wallet.PaymentType
import com.hyunbindev.userserevice.dto.wallet.PaymentEventDto
import com.hyunbindev.userserevice.entity.wallet.PaymentEntity
import com.hyunbindev.userserevice.entity.wallet.WalletEntity
import com.hyunbindev.userserevice.exception.WalletException
import com.hyunbindev.userserevice.repository.wallet.PaymentRepository
import com.hyunbindev.userserevice.repository.wallet.WalletRepository

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

/**
 * ## 사용자 지갑 서비스
 * @since 2026-01-26
 */
@Service
class WalletService(
    private val walletRepository: WalletRepository,
    private val paymentHistoryRepository: PaymentRepository
) {
    /**
     *  ## 사용자 계좌 잔액 조회
     *  @param UUID 서비스 내 사용자 고유 UUID
     *  @return Long current balance
     */
    @Transactional(readOnly = true)
    fun getWalletBalance(userUuid: UUID): Long{
        val wallet: WalletEntity = walletRepository.findById(userUuid)
            .orElseThrow{ WalletException(WalletExceptionConst.NOT_FOUND_ACCOUNT) }

        return wallet.balance
    }

    /**
     * ## 사용자 재화 입금 처리
     *
     *  *  결제 실패 보상트랜잭션을 위한 payment history 로 추가
     *
     * @param UUID 서비스 내 사용자 고유 UUID
     * @since 2026-01-26
     * @return 거래 후 잔액
     */
    @Transactional
    fun deposit(userUuid: UUID, amount:Long): PaymentEventDto {
        val wallet: WalletEntity = walletRepository.findById(userUuid)
            .orElseThrow{ WalletException(WalletExceptionConst.NOT_FOUND_ACCOUNT) }

        //입금 처리
        wallet.deposit(amount)

        //결제 entity
        val payment: PaymentEntity = PaymentEntity(
            wallet = wallet,
            type = PaymentType.DEPOSIT,
            amount = amount
        )

        //결제 entity 영속화
        paymentHistoryRepository.save(payment)

        //입금 후 잔액 반환
        return PaymentEventDto(
            paymentId = payment.id,
            userUuid = wallet.memberId,
            amount = amount,
            paymentType = payment.type,
            walletBalance = wallet.balance
        )
    }

    /**
     *  ## 사용자 재화 출금 처리
     *  
     *  시스템 내부 거래용
     *
     *
     *  *  결제 실패 보상트랜잭션을 위한 payment history 로 추가
     *
     *  @param UUID 서비스 내 사용자 고유 UUID
     *  @return 거래후 잔액
     *  @since 2026-01-26
     *
     */
    @Transactional
    fun withdrawal(userUuid: UUID, amount:Long):PaymentEventDto {
        val wallet = walletRepository.findById(userUuid)
            .orElseThrow{ WalletException(WalletExceptionConst.NOT_FOUND_ACCOUNT) }

        //출금 처리
        wallet.withdraw(amount)

        //결제 entity
        val payment: PaymentEntity = PaymentEntity(
            wallet = wallet,
            type = PaymentType.WITHDRAWAL,
            amount = amount
        )

        //결제 entity 영속화
        paymentHistoryRepository.save(payment)

        return PaymentEventDto(
            paymentId = payment.id,
            userUuid = wallet.memberId,
            amount = amount,
            paymentType = payment.type,
            walletBalance = wallet.balance
        )
    }
}