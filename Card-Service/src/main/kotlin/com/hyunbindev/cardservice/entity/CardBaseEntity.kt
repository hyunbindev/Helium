package com.hyunbindev.cardservice.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class CardBaseEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val name: String,

    @Column(nullable= false)
    val health:Int,

    @Column(nullable = false)
    val attack:Int,

    @Column(nullable = false)
    val defense:Int,

    @Column(nullable = false)
    val criticalRate:Double,

    @Column(nullable = false)
    val criticalDamage: Double,

    @Column(nullable = false)
    val description:String,

    @Column(nullable = false)
    val disabled:Boolean = false
    ) {
}