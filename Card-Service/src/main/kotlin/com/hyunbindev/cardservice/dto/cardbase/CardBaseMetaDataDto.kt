package com.hyunbindev.cardservice.dto.cardbase

data class CardBaseMetaDataDto(
    private val name:String,
    private val health:Int,
    private val attack:Int,
    private val defense:Int,
    private val criticalRate:Double,
    private val description:String,
)