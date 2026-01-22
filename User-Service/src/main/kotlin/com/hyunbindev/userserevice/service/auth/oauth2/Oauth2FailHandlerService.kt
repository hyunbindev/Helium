package com.hyunbindev.userserevice.service.oauth2

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
    }

}