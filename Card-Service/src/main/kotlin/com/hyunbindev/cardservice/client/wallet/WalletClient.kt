package com.hyunbindev.cardservice.client.wallet

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(
    name = "wallet",
    url = $$"${services.wallet-service.url}",
)
interface WalletClient {
    @GetMapping("/api/user/v1/wallet/withdraw")
    fun withdraw(@RequestBody amount:Long)
}