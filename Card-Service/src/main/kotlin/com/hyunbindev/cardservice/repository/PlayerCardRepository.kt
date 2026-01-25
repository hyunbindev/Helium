package com.hyunbindev.cardservice.repository

import com.hyunbindev.cardservice.entity.PlayerCardEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PlayerCardRepository : JpaRepository<PlayerCardEntity, Long> {
}