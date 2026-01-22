package com.hyunbindev.userserevice.service.auth.oauth2

import com.hyunbindev.common_auth_module.constant.OAuth2Provider
import com.hyunbindev.userserevice.entity.member.MemberEntity
import com.hyunbindev.userserevice.security.jwt.JwtProvider
import com.hyunbindev.userserevice.service.auth.OAuthLoginService
import com.hyunbindev.userserevice.service.auth.TokenService
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.stereotype.Service
import org.springframework.web.util.UriComponentsBuilder

/**
 * * OAuth2 SuccessHandler
* oauth2 로그인후 로직
 * 회원 가입및 프로필 업데이트
 * Access token 및 Refresh Token 발급 및 저장
* @author hyunbindev
* @since 2026-01-17
*/
@Service
class Oauth2SuccessHandlerService(
    private val oAuthLoginService: OAuthLoginService,
    private val jwtProvider: JwtProvider,
    private val tokenService: TokenService,
) : SimpleUrlAuthenticationSuccessHandler() {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val provider = OAuth2Provider.from(authentication)

        val oAuthUser = authentication.principal as OAuth2User
        val member: MemberEntity =oAuthLoginService.sign(provider, oAuthUser)

        /**
         * access token 및 refresh token 발급
         */
        val accessToken:String = jwtProvider.createAccessToken(member);
        val refreshToken:String = jwtProvider.createRefreshToken(member.id.toString());

        /**
         * redis에 refresh token 저장
         */
        tokenService.saveRefreshToken(member.id.toString(), refreshToken)

        /**
         * uri query Param으로 Access Token 전달
         * TODO- front uri 연동 필요 임시 uri /front로 리다이렉트 중
         */
        val targetUrl:String = UriComponentsBuilder
            .fromUriString("http://localhost:4848")
            .queryParam("token",accessToken)
            .encode()
            .toUriString()

        /**
         * refresh token cookie 저장
         */
        val cookie = Cookie("refresh_token", refreshToken).apply{
            path = "/"
            isHttpOnly = true
            maxAge = 1800
        }

        response.addCookie(cookie)

        redirectStrategy.sendRedirect(request, response, targetUrl)
    }
}