package com.hyunbindev.userserevice.repository.wallet

import com.hyunbindev.userserevice.entity.wallet.PaymentEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface PaymentRepository : JpaRepository<PaymentEntity, UUID> {
}