package com.hyunbindev.cardservice.controller.public

import com.hyunbindev.cardservice.dto.card.PlayerCardDto
import com.hyunbindev.cardservice.dto.enhance.EnhanceResultDto
import com.hyunbindev.cardservice.dto.stat.IncreaseStatDto
import com.hyunbindev.cardservice.service.cardbase.CardService
import com.hyunbindev.cardservice.service.enhance.CardStatService
import com.hyunbindev.cardservice.service.enhance.EnhanceService
import com.hyunbindev.cardservice.service.playercard.CardGachaService
import com.hyunbindev.cardservice.service.playercard.PlayerCardService
import com.hyunbindev.common_auth_module.annotation.UserUuid
import feign.Response
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/card/v1")
class CardController(
    private val cardGachaService: CardGachaService,
    private val playerCardService: PlayerCardService,
    private val enhanceService: EnhanceService,
    private val cardStatService: CardStatService
) {
    val log = LoggerFactory.getLogger(CardController::class.java)
    @GetMapping("/gacha")
    fun getCardGacha(@UserUuid userUuid: UUID): ResponseEntity<PlayerCardDto> {
        return ResponseEntity.ok(cardGachaService.drawRandCard(userUuid))
    }

    /**
     * 카드 목록 조회
     */
    @GetMapping("/card/{userUuid}")
    fun getCards(@PathVariable userUuid: UUID): ResponseEntity<List<PlayerCardDto>> {
        return ResponseEntity.ok(playerCardService.getPlayerCard(userUuid))
    }

    /**
     * 카드 강화
     */
    @PostMapping("/enhance/{cardId}")
    fun enhanceCard(@UserUuid userUuid: UUID, @PathVariable cardId: Long): ResponseEntity<EnhanceResultDto> {
        return ResponseEntity.ok(enhanceService.enhanceCard(userUuid, cardId))
    }

    /**
     * 스탯 증가
     */
    @PostMapping("/increaseStat/{cardId}")
    fun increaseStat(@UserUuid userUuid: UUID, @PathVariable cardId: Long,@RequestBody dto: IncreaseStatDto): ResponseEntity<PlayerCardDto> {
        return ResponseEntity.ok(cardStatService.increaseStat(userUuid, cardId,dto))
    }
}