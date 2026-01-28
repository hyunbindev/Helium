package com.hyunbindev.cardservice.entity

import com.hyunbindev.cardservice.constant.ImageStatus
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class CardBaseEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grade_id", nullable = false)
    val grade: CardGradeEntity,

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
    val imageKey:String,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val imageStatus:ImageStatus = ImageStatus.PENDING,

    @Column(nullable = false)
    val disabled:Boolean = false
    ) {
}