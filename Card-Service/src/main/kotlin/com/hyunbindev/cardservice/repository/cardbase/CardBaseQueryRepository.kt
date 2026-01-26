package com.hyunbindev.cardservice.repository.cardbase

import com.hyunbindev.cardservice.entity.CardBaseEntity

interface CardBaseQueryRepository {
    fun findRandomOne(): CardBaseEntity?
}