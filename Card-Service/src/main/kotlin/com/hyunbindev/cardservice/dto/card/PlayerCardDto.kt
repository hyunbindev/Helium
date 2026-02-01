package com.hyunbindev.cardservice.dto.card

import com.hyunbindev.cardservice.entity.PlayerCardEntity
import java.util.UUID

data class PlayerCardDto(
    val cardId: Long,
    val imageUrl: String,
    val displayName: String,
    val grade:String,
    val ownerUUID: UUID,
    val health: Int,
    val defense:Int,
    val attack:Int,
    val criticalRate:Double,
    val criticalDamage:Double,
    val level:Int,
    val statPoint:Int,
    val description:String,
){
    companion object {
        fun fromEntity(card: PlayerCardEntity): PlayerCardDto {
            return PlayerCardDto(
                cardId = card.id,
                imageUrl = card.baseCard.imageKey,
                displayName = card.displayName,
                grade = card.baseCard.grade.gradeId,
                ownerUUID = card.owner,
                health = card.health,
                defense = card.defense,
                attack = card.attack,
                criticalDamage = card.criticalDamage,
                criticalRate = card.criticalRate,
                level = card.level,
                statPoint = card.statPoint,
                description = card.baseCard.description
            )
        }
    }
}