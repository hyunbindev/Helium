package com.hyunbindev.userserevice.controller.auth

import com.hyunbindev.common_auth_module.annotation.UserUuid
import com.hyunbindev.userserevice.constant.exception.AuthExceptionConst
import com.hyunbindev.userserevice.exception.AuthException
import com.hyunbindev.userserevice.service.auth.LogOutService
import com.hyunbindev.userserevice.service.auth.TokenService
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * auth controller
 * @author hyunbindev
 * @since 2026-01-18
 */
@RestController
@RequestMapping("/api/user/v1/auth")
class AuthController(
    private val logOutService: LogOutService,
    private val tokenService: TokenService
) {
    /**
     * access token 재발급
     */
    @GetMapping("/getAccessToken")
    fun getAccessToken(auth: Authentication, request: HttpServletRequest):ResponseEntity<String> {

        val cookies:Array<Cookie> = request.cookies ?: arrayOf<Cookie>()
        val refreshTokenCookie = cookies.firstOrNull { it.name == "refresh_token" }
        val refreshToken: String = refreshTokenCookie?.value?: throw AuthException(AuthExceptionConst.REFRESH_TOKEN_NOT_FOUND)

        val accessToken:String = tokenService.reCreateAccessToken(auth.name,refreshToken)

        return ResponseEntity.ok().body(accessToken)
    }

    /**
     * 로그 아웃
     */
    @GetMapping("/logout")
    fun logout(@UserUuid auth: Authentication, response: HttpServletResponse): ResponseEntity<Void> {
        logOutService.logout(auth.name)

        val cookie = Cookie("refresh_token",null).apply{
            isHttpOnly = true
            secure = true
            path="/"
            maxAge = 0
        }
        response.addCookie(cookie)
        return ResponseEntity.accepted().build()
    }
}