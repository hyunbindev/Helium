package com.hyunbindev.userserevice.service.wallet

import com.hyunbindev.userserevice.constant.exception.WalletExceptionConst
import com.hyunbindev.userserevice.entity.wallet.WalletEntity
import com.hyunbindev.userserevice.entity.wallet.WalletTransaction
import com.hyunbindev.userserevice.entity.wallet.WalletTransactionType
import com.hyunbindev.userserevice.exception.WalletException
import com.hyunbindev.userserevice.repository.wallet.WalletRepository
import com.hyunbindev.userserevice.repository.wallet.WalletTransactionRepository

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
    private val walletTransactionRepository: WalletTransactionRepository,
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
     * 시스템 내부 거래용
     *
     * @param UUID 서비스 내 사용자 고유 UUID
     * @since 2026-01-26
     * @return 거래 후 잔액
     */
    @Transactional
    fun deposit(userUuid: UUID, amount:Long):Long {
        val wallet: WalletEntity = walletRepository.findById(userUuid)
            .orElseThrow{ WalletException(WalletExceptionConst.NOT_FOUND_ACCOUNT) }

        //입금 전 계좌 잔액
        val beforeBalance:Long = wallet.balance

        //입금 처리
        wallet.deposit(amount)

        //입금 기록
        val walletTransactionHistory = WalletTransaction(
            wallet = wallet,
            amount = amount,
            type = WalletTransactionType.DEPOSIT,
            balanceBefore = beforeBalance,
            balanceAfter = wallet.balance,
            //시스템 거래 지급인 미거래
            targetMemberId = null,
        )

        //입금 기록 영속화
        walletTransactionRepository.save(walletTransactionHistory)

        //입금 후 잔액 반환
        return wallet.balance
    }

    /**
     *  ## 사용자 재화 출금 처리
     *  
     *  시스템 내부 거래용
     *  
     *  @param UUID 서비스 내 사용자 고유 UUID
     *  @return 거래후 잔액
     *  @since 2026-01-26
     */
    @Transactional
    fun withdrawal(userUuid: UUID, amount:Long):Long {
        val wallet = walletRepository.findById(userUuid)
            .orElseThrow{ WalletException(WalletExceptionConst.NOT_FOUND_ACCOUNT) }

        //출금 전 잔액
        val beforeBalance:Long = wallet.balance

        //출금 처리
        wallet.withdraw(amount)

        val walletTransactionHistory = WalletTransaction(
            wallet = wallet,
            amount = amount,
            type = WalletTransactionType.WITHDRAWAL,
            balanceBefore = beforeBalance,
            balanceAfter = wallet.balance,
            //시스템 거래 수취인 미기입
            targetMemberId = null,
        )

        //출금 기록 영속화
        walletTransactionRepository.save(walletTransactionHistory)

        return wallet.balance
    }
}