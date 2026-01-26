package com.hyunbindev.userserevice.repository.wallet

import com.hyunbindev.userserevice.entity.wallet.WalletTransaction
import org.springframework.data.jpa.repository.JpaRepository

interface WalletTransactionRepository : JpaRepository<WalletTransaction, Long> {
}