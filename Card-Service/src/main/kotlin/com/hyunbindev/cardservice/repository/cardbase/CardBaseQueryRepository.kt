package com.hyunbindev.cardservice.repository.cardbase

import com.hyunbindev.cardservice.entity.CardBaseEntity
import com.hyunbindev.cardservice.entity.CardGradeEntity

interface CardBaseQueryRepository {
    fun findRandomOneByGrade(grade: CardGradeEntity): CardBaseEntity?
}