package com.hyunbindev.userserevice.service.auth.oauth2

import com.hyunbindev.userserevice.constant.exception.AuthExceptionConst
import com.hyunbindev.userserevice.exception.AuthException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.stereotype.Service

@Service
class Oauth2FailHandlerService: AuthenticationFailureHandler {
    private val logger = LoggerFactory.getLogger(Oauth2FailHandlerService::class.java)
    override fun onAuthenticationFailure(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: AuthenticationException
    ) {
        logger.debug(exception.toString());
        throw AuthException(AuthExceptionConst.INTERAL_SERVER_ERROR,"OAuth2 인증 실패",true);
    }
}