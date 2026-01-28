package com.hyunbindev.cardservice.client.wallet

import com.hyunbindev.cardservice.dto.payment.PaymentEventDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

/**
 * # user micro service wallet 호출
 * @author hyunbindev
 */
@FeignClient(
    name = "wallet",
    url = $$"${services.wallet-service.url}",
)
interface WalletClient {
    /**
     * ### 출금 호출
     */
    @PostMapping("/api/user/v1/wallet/withdraw")
    fun withDraw(@RequestBody amount:Long): PaymentEventDto

    /**
     * ### 입금 호출
     */
    @PostMapping("/api/user/v1/wallet/deposit")
    fun withDeposit(@RequestBody amount:Long): PaymentEventDto
}