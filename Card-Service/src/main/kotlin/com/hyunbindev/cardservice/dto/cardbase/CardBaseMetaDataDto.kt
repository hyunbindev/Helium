package com.hyunbindev.cardservice.dto.cardbase

data class CardBaseMetaDataDto(
    val name:String,
    val health:Int,
    val attack:Int,
    val defense:Int,
    val criticalRate:Double,
    val description:String,
    val criticalDamage:Double,
)