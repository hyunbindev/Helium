package com.hyunbindev.cardservice.service.enhance

import com.hyunbindev.cardservice.client.wallet.WalletClient
import com.hyunbindev.cardservice.constant.enhance.EnhanceResult
import com.hyunbindev.cardservice.constant.exception.CardExceptionConst
import com.hyunbindev.cardservice.dto.card.PlayerCardDto
import com.hyunbindev.cardservice.dto.enhance.EnhanceResultDto
import com.hyunbindev.cardservice.dto.payment.PaymentEventDto
import com.hyunbindev.cardservice.entity.PlayerCardEntity
import com.hyunbindev.cardservice.exception.CardException
import com.hyunbindev.cardservice.repository.playercard.PlayerCardRepository
import jakarta.transaction.Transactional
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import java.util.UUID
import kotlin.math.exp
import kotlin.math.ln
import kotlin.math.min
import kotlin.math.pow

@Service
class EnhanceService(
    private val playerCardRepository: PlayerCardRepository,
    private val walletClient: WalletClient,
    private val eventPublisher: ApplicationEventPublisher,
) {
    //카드 강화
    @Transactional
    fun enhanceCard(userUuid: UUID, cardId:Long): EnhanceResultDto {
        //초기 비용
        val initCost:Long = 500

        // 카드 조회
        val playerCard: PlayerCardEntity = playerCardRepository.findByIdFetchJoin(cardId).orElseThrow{
            throw CardException(CardExceptionConst.CARD_NOT_FOUND)
        }

        if(playerCard.upgradeRequired) throw CardException(CardExceptionConst.UPGRADE_REQURE_CARD)

        //카드 소유 검증
        if(playerCard.owner != userUuid) throw CardException(CardExceptionConst.NOT_OWNED_CARD)

        //레벨 기반 비용 증가
        val cost: Long = (initCost * 1.5.pow(playerCard.level.toDouble())).toLong()

        //user service 결제 요청
        val payment: PaymentEventDto = walletClient.withDraw(cost)

        val result = calculateEnhanceResult(playerCard.level)
        eventPublisher.publishEvent(payment)


        when(result){
            EnhanceResult.SUCCESS->{
                //강화 성공 로직
                playerCard.upgradeRequired = true
                playerCard.level++
                //스텟 증가
            }
            EnhanceResult.KEEP->{

            }
            EnhanceResult.DESTROY->{
                playerCard.active = false
            }
        }

        return EnhanceResultDto(
            cardInfo = PlayerCardDto.fromEntity(playerCard),
            result = result.name,
        )
    }

    private fun calculateEnhanceResult(level:Int): EnhanceResult {
        val rand = (1..1000000).random()
        val k = 0.15
        val successRate = (95.0 * exp(-k * level))+5.0

        val destroyRate = if (level < 5) 0.0 else min(1.0 + ln(level.toDouble()) * 5, 35.0)
        val successThreshold = successRate * 10000
        val destroyThreshold = destroyRate * 10000
        return when {
            rand <= successThreshold -> EnhanceResult.SUCCESS
            rand <= successThreshold + destroyThreshold -> EnhanceResult.DESTROY
            else -> EnhanceResult.KEEP
        }
    }
}
