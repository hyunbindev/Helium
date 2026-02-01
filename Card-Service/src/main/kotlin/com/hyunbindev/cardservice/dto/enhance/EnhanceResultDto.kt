package com.hyunbindev.cardservice.dto.enhance

import com.hyunbindev.cardservice.constant.enhance.EnhanceResult

data class EnhanceResultDto(
    val enhanceResult: EnhanceResult,
    val result:String
) {
}
data class UpgradeSelectionResponse(
    val description: String,
    val stat:String,
    val incrementAmount:Int
)