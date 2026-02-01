package com.hyunbindev.cardservice.repository.cardbase

import com.hyunbindev.cardservice.entity.CardGradeEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CardGradeRepository : JpaRepository<CardGradeEntity,Long>{
    fun findAllByOrderByWeightAsc():List<CardGradeEntity>
}