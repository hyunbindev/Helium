package com.hyunbindev.userserevice.dto.wallet

import com.hyunbindev.userserevice.constant.exception.WalletExceptionConst
import java.time.LocalDateTime

data class WalletExceptionResponse(
    val code: String,
    val message: String,
    val timestamp: LocalDateTime = LocalDateTime.now()
)
