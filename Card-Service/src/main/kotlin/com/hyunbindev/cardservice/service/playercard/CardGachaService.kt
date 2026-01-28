package com.hyunbindev.cardservice.service.playercard

import com.hyunbindev.cardservice.client.wallet.WalletClient
import com.hyunbindev.cardservice.constant.exception.CardExceptionConst
import com.hyunbindev.cardservice.dto.card.PlayerCardDto
import com.hyunbindev.cardservice.dto.payment.PaymentEventDto
import com.hyunbindev.cardservice.entity.CardBaseEntity
import com.hyunbindev.cardservice.entity.PlayerCardEntity
import com.hyunbindev.cardservice.exception.CardException
import com.hyunbindev.cardservice.repository.playercard.PlayerCardRepository
import com.hyunbindev.cardservice.repository.cardbase.CardBaseRepository
import com.hyunbindev.cardservice.service.cardbase.CardService
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service()
class CardGachaService(
    private val cardBaseRepository: CardBaseRepository,
    private val playerCardRepository: PlayerCardRepository,
    private val walletClient: WalletClient,
    private val eventPublisher: ApplicationEventPublisher,
) {
    private val log = LoggerFactory.getLogger(javaClass)
    /**
     * ### 신규 카드 뽑기 랜덤
     */
    @Transactional
    fun drawRandCard(userUuid: UUID): PlayerCardDto {
        val price: Long = 1000L
        val payment: PaymentEventDto = walletClient.withDraw(price)
        //결제 내부 이벤트 발행
        eventPublisher.publishEvent(payment)


        //결제 이벤트

        val baseEntity:CardBaseEntity = cardBaseRepository.findRandomOne()?:run {
            //기본 카드 베이스 존재 하지 않을 시
            log.error("[Gacha] Database is empty! No CardBaseEntity found.")
            throw CardException(CardExceptionConst.INTERNAL_SERVER_ERROR)
        }

        var playerCard: PlayerCardEntity = PlayerCardEntity.createFromBase(baseEntity,userUuid)

        //사용자 카드 영속화
        playerCard = playerCardRepository.save(playerCard)


        //뽑기 결과 반환
        return PlayerCardDto.fromEntity(playerCard)
    }
}