package kr.co.vacgom.api.user.exception

import kr.co.vacgom.api.global.exception.error.ErrorCode
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.NOT_FOUND

enum class UserError(
    override val message: String,
    override val status: HttpStatus,
    override val code: String,
) : ErrorCode {
    USER_NOT_FOUND("회원이 존재하지 않습니다.", NOT_FOUND, "M_001"),
    SOCIAL_ID_NOT_FOUND("소셜 ID가 존재하지 않습니다.", NOT_FOUND, "M_002")
}
