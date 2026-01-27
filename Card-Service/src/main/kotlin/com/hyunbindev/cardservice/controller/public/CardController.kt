package com.hyunbindev.cardservice.controller.public

import com.hyunbindev.cardservice.service.cardbase.CardService
import com.hyunbindev.cardservice.service.playercard.CardGachaService
import com.hyunbindev.common_auth_module.annotation.UserUuid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/card/v1/")
class CardController(
    private val cardService: CardService,
    private val cardGachaService: CardGachaService
) {
    @GetMapping("/gacha")
    fun getCardGacha(@UserUuid userUuid: UUID): ResponseEntity<Void> {
        cardGachaService.drawRandCard(userUuid)
        return ResponseEntity.ok().build()
    }
}