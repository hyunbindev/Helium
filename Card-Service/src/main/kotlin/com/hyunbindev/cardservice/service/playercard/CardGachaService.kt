package com.hyunbindev.cardservice.service.playercard

import com.hyunbindev.cardservice.client.wallet.WalletClient
import com.hyunbindev.cardservice.dto.card.PlayerCardDto
import com.hyunbindev.cardservice.entity.CardBaseEntity
import com.hyunbindev.cardservice.entity.PlayerCardEntity
import com.hyunbindev.cardservice.repository.playercard.PlayerCardRepository
import com.hyunbindev.cardservice.repository.cardbase.CardBaseRepository
import com.hyunbindev.cardservice.service.cardbase.CardService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service()
class CardGachaService(
    private val cardBaseRepository: CardBaseRepository,
    private val playerCardRepository: PlayerCardRepository,
    private val walletClient: WalletClient
) {
    /**
     * ### 신규 카드 뽑기 랜덤
     */
    @Transactional
    fun drawRandCard(userUuid: UUID): PlayerCardDto {
        val price: Long = 1000L
        walletClient.withDraw(price)
        val baseEntity:CardBaseEntity = cardBaseRepository.findRandomOne()?:throw RuntimeException("저장된 카드 없음")

        var playerCard: PlayerCardEntity = PlayerCardEntity.createFromBase(baseEntity,userUuid)

        playerCard = playerCardRepository.save(playerCard)

        return PlayerCardDto(
            cardId = playerCard.id,
            displayName = playerCard.displayName,
            //TODO - 임시 이미지 키만 전달 이후 URL로 바꿔 리턴 해야함
            imageUrl = playerCard.baseCard.imageKey,
            ownerUUID = playerCard.owner,
            health = playerCard.health,
            defense = playerCard.defense,
            attack = playerCard.attack,
            criticalRate = playerCard.criticalRate,
            criticalDamage = playerCard.criticalDamage,
            level = playerCard.level,
            description = baseEntity.description,
        )
    }
}