package com.hyunbindev.userserevice.service.wallet

import com.hyunbindev.userserevice.entity.wallet.WalletEntity
import com.hyunbindev.userserevice.entity.wallet.WalletTransaction
import com.hyunbindev.userserevice.entity.wallet.WalletTransactionType
import com.hyunbindev.userserevice.repository.wallet.WalletTransactionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class WalletTransactionHistoryService(
    private val walletTransactionRepository: WalletTransactionRepository
) {
    @Transactional
    fun record(walletEntity: WalletEntity, amount: Long, type: WalletTransactionType, targetMemberId: UUID?) {
        val transaction:WalletTransaction=WalletTransaction(
            wallet=walletEntity,
            amount=amount,
            type=type,

            targetMemberId=targetMemberId
        )
    }
}