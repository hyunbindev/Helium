package com.hyunbindev.common_exception_module

import org.springframework.http.HttpStatus
/**
 * ### 프로젝트 전역 최상위 예외 클래스
 * 모든 도메인 비즈니스 예외는 이 클래스를 상속받아야 합니다.
 * *  **핵심 설계:** * * 1. [ExceptionConstant]를 통해 규격화된 에러 정보를 주입받습니다.
 * *  필요에 따라 [userMessage]를 동적으로 덮어쓸 수 있습니다..
 * * ---
 * ### 사용 예시
 * ```kotlin
 * class CardNotFoundException : BaseException(CardErrorCode.CARD_NOT_FOUND)
 * ```
 * * @property status HTTP 응답 상태 코드
 * @property userMessage 사용자에게 노출할 최종 메시지
 * @property code 에러 식별 코드 (Enum name 또는 "UNKNOWN")
 */
abstract class BaseException(
    exceptionConstant: ExceptionConstant,
    userMessage: String? = null
) : RuntimeException(exceptionConstant.getMessage()) {
    val status: HttpStatus = exceptionConstant.getStatus()
    val userMessage: String = userMessage ?: exceptionConstant.getUserMessage()
    val code: String = if (exceptionConstant is Enum<*>) exceptionConstant.name else "UNKNOWN"
}