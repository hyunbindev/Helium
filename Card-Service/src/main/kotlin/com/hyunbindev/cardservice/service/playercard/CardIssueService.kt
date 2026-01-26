package com.hyunbindev.cardservice.service.playercard

import com.hyunbindev.cardservice.entity.CardBaseEntity
import com.hyunbindev.cardservice.entity.PlayerCardEntity
import com.hyunbindev.cardservice.repository.PlayerCardRepository
import com.hyunbindev.cardservice.repository.cardbase.CardBaseRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CardIssueService(
    private val cardBaseRepository: CardBaseRepository,
    private val playerCardRepository: PlayerCardRepository
) {
    public fun issueCard(userId:UUID, cardBaseId:Long){
        val cardBaseEntity: CardBaseEntity = cardBaseRepository.findById(cardBaseId)
            .orElseThrow{RuntimeException()}

        var playerCard: PlayerCardEntity = PlayerCardEntity.createFromBase(cardBaseEntity,userId)

        playerCard = playerCardRepository.save(playerCard)
    }
}