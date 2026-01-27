package com.hyunbindev.cardservice.dto.stat

data class IncreaseStatDto(
    val health:Int?,
    val defense:Int?,
    val attack:Int?,
    val criticalRate:Int?,
    val criticalDamage:Int?,
) {
}