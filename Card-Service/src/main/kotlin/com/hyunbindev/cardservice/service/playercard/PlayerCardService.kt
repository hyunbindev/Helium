package com.hyunbindev.cardservice.service.playercard

import com.hyunbindev.cardservice.dto.card.PlayerCardDto
import com.hyunbindev.cardservice.entity.PlayerCardEntity
import com.hyunbindev.cardservice.repository.playercard.PlayerCardRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PlayerCardService(
    private val playerCardRepository: PlayerCardRepository
) {
    fun getPlayerCard(userUuid: UUID) {
        val cards:List<PlayerCardEntity> = playerCardRepository.findAllByOwner(userUuid)
    }
}