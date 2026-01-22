package com.hyunbindev.userserevice.constant.exception

import org.springframework.http.HttpStatus

enum class MemberExceptionConst(
    private val message: String,
    private val status: HttpStatus,
    private val userDefaultMessage: String
) : ExceptionConstant{
    MEMBER_NOT_FOUND("member not found in database", HttpStatus.NOT_FOUND,"존재하지 않는 사용자 입니다."),
    MEMBER_NOT_SIGNED("user is not member",HttpStatus.UNAUTHORIZED,"가입되어 있지 않은 사용자 입니다.");
    override fun getMessage(): String =message

    override fun getStatus(): HttpStatus = status

    override fun getUserMessage(): String = userDefaultMessage

}