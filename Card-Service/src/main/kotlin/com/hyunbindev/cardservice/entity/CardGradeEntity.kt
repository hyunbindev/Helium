package com.hyunbindev.cardservice.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.awt.Color

@Entity
@Table(name = "card_grade")
class CardGradeEntity(
    @Id
    @Column(nullable = false)
    val gradeId: String,

    @Column(nullable = false)
    val weight: Int,

    @Column(nullable = false)
    val colorCode: String,

    @Column(nullable = false)
    val enhanceRate: Double,
) {
}