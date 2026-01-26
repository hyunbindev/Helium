package com.hyunbindev.cardservice.service.playercard

import com.hyunbindev.cardservice.client.wallet.WalletClient
import com.hyunbindev.cardservice.entity.CardBaseEntity
import com.hyunbindev.cardservice.entity.PlayerCardEntity
import com.hyunbindev.cardservice.repository.PlayerCardRepository
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
    fun drawRandCard(userUuid: UUID){
        val price: Long = 1000L
        walletClient.withDraw(price)
        val baseEntity:CardBaseEntity = cardBaseRepository.findRandomOne()?:throw RuntimeException("저장된 카드 없음")

        val playerCard: PlayerCardEntity = PlayerCardEntity.createFromBase(baseEntity,userUuid)

        playerCardRepository.save(playerCard)
    }
}