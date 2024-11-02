package kr.co.vacgom.api.auth.jwt.exception

import kr.co.vacgom.api.global.exception.error.ErrorCode
import org.springframework.http.HttpStatus

enum class JwtError(
    override val message: String,
    override val status: HttpStatus,
    override val code: String,
) : ErrorCode{
    JWT_EXCEPTION("JWT 예외가 발생했습니다.", HttpStatus.BAD_REQUEST, "J_000"),
    EXPIRED_JWT("만료된 토큰입니다.", HttpStatus.UNAUTHORIZED, "J_001"),
    MALFORMED_JWT("JWT 형식이 올바르지 않습니다.", HttpStatus.UNAUTHORIZED, "J_002"),
    SIGNATURE("서명이 유효하지 않습니다.", HttpStatus.UNAUTHORIZED, "J_003"),
    UNSUPPORTED_JWT("지원하지 않는 JWT 형식입니다.", HttpStatus.UNAUTHORIZED, "J_004"),
}
