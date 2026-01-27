package com.hyunbindev.cardservice.service.playercard

import com.hyunbindev.cardservice.client.wallet.WalletClient
import com.hyunbindev.cardservice.constant.CardExceptionConst
import com.hyunbindev.cardservice.dto.card.PlayerCardDto
import com.hyunbindev.cardservice.entity.CardBaseEntity
import com.hyunbindev.cardservice.entity.PlayerCardEntity
import com.hyunbindev.cardservice.exception.CardException
import com.hyunbindev.cardservice.repository.playercard.PlayerCardRepository
import com.hyunbindev.cardservice.repository.cardbase.CardBaseRepository
import com.hyunbindev.cardservice.service.cardbase.CardService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service()
class CardGachaService(
    private val cardBaseRepository: CardBaseRepository,
    private val playerCardRepository: PlayerCardRepository,
    private val walletClient: WalletClient
) {
    private val log = LoggerFactory.getLogger(javaClass)
    /**
     * ### 신규 카드 뽑기 랜덤
     */
    @Transactional
    fun drawRandCard(userUuid: UUID): PlayerCardDto {
        val price: Long = 1000L
        walletClient.withDraw(price)

        val baseEntity:CardBaseEntity = cardBaseRepository.findRandomOne()?:run {
            //기본 카드 베이스 존재 하지 않을 시
            log.error("[Gacha] Database is empty! No CardBaseEntity found.")
            throw CardException(CardExceptionConst.INTERNAL_SERVER_ERROR)
        }

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