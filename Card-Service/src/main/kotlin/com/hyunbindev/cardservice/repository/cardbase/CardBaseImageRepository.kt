package com.hyunbindev.cardservice.repository.cardbase

import com.hyunbindev.cardservice.entity.CardBaseImageEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CardBaseImageRepository :JpaRepository<CardBaseImageEntity,Long>{
}