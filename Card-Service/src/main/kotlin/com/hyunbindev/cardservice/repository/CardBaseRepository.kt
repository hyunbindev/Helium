package com.hyunbindev.cardservice.repository

import com.hyunbindev.cardservice.entity.CardBaseEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CardBaseRepository : JpaRepository<CardBaseEntity, Long> {
}