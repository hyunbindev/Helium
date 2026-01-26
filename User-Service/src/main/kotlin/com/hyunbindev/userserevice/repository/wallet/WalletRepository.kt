package com.hyunbindev.userserevice.repository.wallet

import com.hyunbindev.userserevice.entity.wallet.WalletEntity
import com.hyunbindev.userserevice.entity.wallet.WalletTransaction
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface WalletRepository: JpaRepository<WalletEntity, UUID> {
}