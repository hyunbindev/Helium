package com.hyunbindev.cardservice.service.playercard

import com.hyunbindev.cardservice.dto.card.PlayerCardDto
import com.hyunbindev.cardservice.entity.PlayerCardEntity
import com.hyunbindev.cardservice.repository.playercard.PlayerCardRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class PlayerCardService(
    private val playerCardRepository: PlayerCardRepository
) {
    //사용자 보유 카드 조회
    @Transactional(readOnly = true)
    fun getPlayerCard(userUuid: UUID): List<PlayerCardDto> {
        val cards:List<PlayerCardEntity> = playerCardRepository.findAllByOwner(userUuid)
        return cards.map{
            PlayerCardDto.fromEntity(it)
        }
    }
}