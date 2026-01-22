package com.hyunbindev.userserevice.service.auth

import org.springframework.stereotype.Service

@Service
class LogOutService(
    private val tokenService:TokenService,
) {
    public fun logout(userId:String){
        tokenService.deleteRefreshToken(userId);
    }
}