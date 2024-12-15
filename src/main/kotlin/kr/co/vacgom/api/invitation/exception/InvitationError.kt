package kr.co.vacgom.api.invitation.exception

import kr.co.vacgom.api.global.exception.error.ErrorCode
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.NOT_FOUND

enum class InvitationError(
    override val message: String,
    override val status: HttpStatus,
    override val code: String,
): ErrorCode {
    INVITATION_CODE_NOT_FOUND("초대 코드가 존재하지 않습니다.", NOT_FOUND, "I_001")
}
