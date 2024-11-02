package kr.co.vacgom.api.member.exception

import kr.co.vacgom.api.global.exception.error.ErrorCode
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.NOT_FOUND

enum class MemberError(
    override val message: String,
    override val status: HttpStatus,
    override val code: String,
) : ErrorCode {
    MEMBER_NOT_FOUND("회원이 존재하지 않습니다.", NOT_FOUND, "M_001")
}
