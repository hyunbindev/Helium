package com.hyunbindev.cardservice.dto.card

import java.util.UUID

data class PlayerCardDto(
    val cardId: Long,
    val imageUrl: String,
    val displayName: String,
    val ownerUUID: UUID,
    val health: Int,
    val defense:Int,
    val attack:Int,
    val criticalRate:Double,
    val criticalDamage:Double,
    val level:Int,
    val description:String,
)