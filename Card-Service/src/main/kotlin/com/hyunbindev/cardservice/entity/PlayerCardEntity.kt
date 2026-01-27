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
    var displayName: String,

    @Column(nullable = false)
    var owner: UUID,

    @Column(nullable = false)
    var health:Int,

    @Column(nullable = false)
    var defense:Int,

    @Column(nullable = false)
    var attack:Int,

    @Column(nullable = false)
    var criticalRate:Double,

    @Column(nullable = false)
    var criticalDamage:Double,

    @Column(nullable = false)
    var level:Int,

    @Column(nullable = false)
    var statPoint:Int = 0,

    @Column(nullable = false)
    var active:Boolean = true
    ) {
    fun increaseHealth(point:Int) {
        health += point*10
    }
    fun increaseAttack(point:Int){
        attack += point*10
    }
    fun increaseCriticalRate(point:Int){
        criticalRate += point*1.5
    }
    fun increaseCriticalDamage(point:Int){
        criticalDamage += point*0.5
    }

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