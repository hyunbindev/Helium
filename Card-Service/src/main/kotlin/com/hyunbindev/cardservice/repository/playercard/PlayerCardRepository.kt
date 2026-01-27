package com.hyunbindev.cardservice.repository.playercard

import com.hyunbindev.cardservice.entity.PlayerCardEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface PlayerCardRepository : JpaRepository<PlayerCardEntity, Long> {
    fun findAllByOwner(userUuid: UUID): List<PlayerCardEntity>
}