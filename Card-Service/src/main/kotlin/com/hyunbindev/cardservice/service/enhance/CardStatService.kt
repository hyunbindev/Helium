package com.hyunbindev.cardservice.service.enhance

import com.hyunbindev.cardservice.constant.CardExceptionConst
import com.hyunbindev.cardservice.dto.card.PlayerCardDto
import com.hyunbindev.cardservice.dto.stat.IncreaseStatDto
import com.hyunbindev.cardservice.entity.PlayerCardEntity
import com.hyunbindev.cardservice.exception.CardException
import com.hyunbindev.cardservice.repository.playercard.PlayerCardRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class CardStatService(
    private val playerCardRepository: PlayerCardRepository,
) {
    @Transactional
    fun increaseStat(userUuid: UUID, cardId:Long, dto: IncreaseStatDto): PlayerCardDto{
        val playerCard: PlayerCardEntity = playerCardRepository.findByIdFetchJoin(cardId)
            .orElseThrow{ CardException(CardExceptionConst.CARD_NOT_FOUND) }

        if(playerCard.owner != userUuid){
            throw CardException(CardExceptionConst.NOT_OWNED_CARD)
        }
        //total stat 검증
        val totalStat =
            (dto.health ?: 0) +
            (dto.attack ?: 0) +
            (dto.criticalRate ?: 0) +
            (dto.criticalDamage ?: 0)

        if(totalStat == 0)
            throw CardException(CardExceptionConst.INVALID_REQUEST,"증가 스탯이 없습니다.")

        if(playerCard.statPoint < totalStat)
            throw CardException(CardExceptionConst.INVALID_REQUEST,"스탯 강화 포인트가 맞지않습니다. 다시 시도해 주세요.")


        dto.health?.let {
            playerCard.increaseHealth(it)
        }
        dto.attack?.let {
            playerCard.increaseAttack(it)
        }
        dto.criticalRate?.let {
            playerCard.increaseCriticalRate(it)
        }
        dto.criticalDamage?.let {
            playerCard.increaseCriticalDamage(it)
        }

        playerCard.statPoint -= totalStat

        return PlayerCardDto.fromEntity(playerCard)
    }
}