package com.hyunbindev.cardservice.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.util.UUID

@Entity
class PlayerCardEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long= 0,

    @ManyToOne(fetch = FetchType.LAZY)
    val baseCard: CardBaseEntity,

    @Column(nullable = false)
    val displayName: String,

    @Column(nullable = false)
    val owner: UUID,

    @Column(nullable = false)
    val health:Int,

    @Column(nullable = false)
    val defense:Int,

    @Column(nullable = false)
    val attack:Int,

    @Column(nullable = false)
    val criticalRate:Double,

    @Column(nullable = false)
    val criticalDamage:Double,

    @Column(nullable = false)
    val level:Int,
    ) {
    companion object {
        fun createFromBase(base: CardBaseEntity, owner: UUID): PlayerCardEntity {
            return PlayerCardEntity(
                baseCard = base,
                displayName= base.name,
                owner = owner,
                health = base.health,
                attack = base.attack,
                defense = base.defense,
                criticalRate = base.criticalRate,
                criticalDamage = base.criticalDamage,
                level = 1,
            )
        }
    }
}