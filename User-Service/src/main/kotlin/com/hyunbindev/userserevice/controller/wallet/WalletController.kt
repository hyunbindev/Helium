package com.hyunbindev.userserevice.controller.wallet

import com.hyunbindev.common_auth_module.annotation.UserUuid
import com.hyunbindev.userserevice.service.wallet.WalletService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

/**
 * ### Wallet Controller
 * @since 2026-01-26
 */
@RestController
@RequestMapping("/api/user/v1/wallet")
class WalletController(
    private val walletService: WalletService
) {
    @GetMapping("/balance/me")
    fun getWalletBalanceMe(@UserUuid userUuid: UUID): ResponseEntity<Long> {
        return ResponseEntity.ok(walletService.getWalletBalance(userUuid))
    }

    @GetMapping("/balance/{userUuid}")
    fun getWalletBalance(@PathVariable userUuid:UUID): ResponseEntity<Long> {
        return ResponseEntity.ok(walletService.getWalletBalance(userUuid))
    }

    @PostMapping("/withdraw")
    fun withdraw(@UserUuid userUuid: UUID, @RequestBody amount:Long): ResponseEntity<Long> {
        return ResponseEntity.ok(walletService.withdrawal(userUuid,amount))
    }

    @PostMapping("/deposit")
    fun deposit(@UserUuid userUuid: UUID, @RequestBody amount:Long): ResponseEntity<Long> {
        return ResponseEntity.ok(walletService.deposit(userUuid,amount))
    }
}