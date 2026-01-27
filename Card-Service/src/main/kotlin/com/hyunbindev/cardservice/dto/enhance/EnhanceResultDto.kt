package com.hyunbindev.cardservice.dto.enhance

import com.hyunbindev.cardservice.dto.card.PlayerCardDto

data class EnhanceResultDto(
    val cardInfo: PlayerCardDto,
    val result:String
) {
}