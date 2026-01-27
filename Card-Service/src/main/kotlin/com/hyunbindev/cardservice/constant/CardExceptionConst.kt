package com.hyunbindev.cardservice.constant

import com.hyunbindev.common_exception_module.ExceptionConstant
import org.springframework.http.HttpStatus

enum class CardExceptionConst(
    private val message: String,
    private val status: HttpStatus,
    private val userDefaultMessage: String
):ExceptionConstant {
    CARD_NOT_FOUND("Card not found", HttpStatus.NOT_FOUND,"카드를 찾을 수 없습니다."),
    NOT_OWNED_CARD("This card is not owned by the user", HttpStatus.FORBIDDEN,"소유하지 않은 카드 입니다."),
    INTERNAL_SERVER_ERROR("Interal Server Error", HttpStatus.INTERNAL_SERVER_ERROR,"서버 내부 오류 잠시 후 다시 시도해주세요");
    override fun getMessage(): String = message
    override fun getStatus(): HttpStatus = status
    override fun getUserMessage(): String = userDefaultMessage
}