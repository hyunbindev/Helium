package com.hyunbindev.userserevice.entity.wallet

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime
import java.util.UUID

/**
 * ## 재화 거래 내역 Entity
 * @author hyunbindev
 * @since 2026-01-26
 */
@Entity
class WalletTransaction(
    @ManyToOne(fetch = FetchType.LAZY)
    val wallet: WalletEntity,

    @Column(nullable = false)
    val amount: Long,

    @Column(nullable = false)
    val balanceAfter:Long,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val type:WalletTransactionType,

    //없을시 System 거래
    @Column
    val targetMemberId: UUID?

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
}

enum class WalletTransactionType {
    WITHDRAWAL,
    DEPOSIT,
    TRANSFER_SENT,
    TRANSFER_RECEIVED,
}