package com.hyunbindev.cardservice.constant.exception

import com.hyunbindev.common_exception_module.ExceptionConstant
import org.springframework.http.HttpStatus

enum class CardExceptionConst(
    private val message: String,
    private val status: HttpStatus,
    private val userDefaultMessage: String
): ExceptionConstant {
    UPGRADE_REQURE_CARD("this card is upgrade required.", HttpStatus.CONFLICT,"스탯 업그래이드가 우선 필요 합니다."),
    DUPLICATED_CARD_NAME("conflicted card name",HttpStatus.BAD_REQUEST,""),
    CARD_NOT_FOUND("Card not found", HttpStatus.NOT_FOUND,"카드를 찾을 수 없습니다."),
    NOT_OWNED_CARD("This card is not owned by the user", HttpStatus.FORBIDDEN,"소유하지 않은 카드 입니다."),
    INTERNAL_SERVER_ERROR("Interal Server Error", HttpStatus.INTERNAL_SERVER_ERROR,"서버 내부 오류 잠시 후 다시 시도해주세요"),
    INVALID_REQUEST("Invalid Request", HttpStatus.BAD_REQUEST,"잘못된 요청");
    override fun getMessage(): String = message
    override fun getStatus(): HttpStatus = status
    override fun getUserMessage(): String = userDefaultMessage
}