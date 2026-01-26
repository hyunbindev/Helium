package com.hyunbindev.userserevice.controller.wallet

import com.hyunbindev.common_auth_module.annotation.UserUuid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

/**
 * ### Wallet Controller
 * @since 2026-01-26
 */
@RestController
@RequestMapping("/api/user/v1/wallet")
class WalletController {
    @GetMapping("/balance/me")
    fun getWalletBalanceMe(@UserUuid userUuid: UUID): ResponseEntity<Long> {

    }

    @GetMapping("/balance/{userUuid}")
    fun getWalletBalance(): ResponseEntity<Long> {

    }

    @PostMapping("/withdraw")
    fun withdraw(@UserUuid userUuid: UUID): ResponseEntity<Void> {

    }

    @PostMapping("/deposit")
    fun deposit(@UserUuid userUuid: UUID): ResponseEntity<Void> {

    }

    @PostMapping("/transfer")
    fun transfer(@UserUuid userUuid: UUID): ResponseEntity<Void> {

    }
}