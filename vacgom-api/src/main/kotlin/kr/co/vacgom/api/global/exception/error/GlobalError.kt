package kr.co.vacgom.api.global.exception.error

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.NOT_FOUND

enum class GlobalError(
    override val message: String,
    override val status: HttpStatus,
    override val code: String,
) : ErrorCode {
    GLOBAL_NOT_FOUND("리소스가 존재하지 않습니다.", NOT_FOUND, "G_001"),
    INVALID_REQUEST_PARAM("요청 파라미터가 유효하지 않습니다.", BAD_REQUEST, "G_002"),
    INTERNAL_SERVER_ERROR("서버 내부 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR, "G_003"),
    UNAUTHORIZED("서버 접근 권한이 없습니다.", HttpStatus.UNAUTHORIZED, "G_004"),
}
