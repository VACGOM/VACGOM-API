package kr.co.vacgom.api.baby.exceptioin

import kr.co.vacgom.api.global.exception.error.ErrorCode
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.NOT_FOUND

enum class BabyError(
    override val message: String,
    override val status: HttpStatus,
    override val code: String,
) : ErrorCode {
    BABY_NOT_FOUND("아기가 존재하지 않습니다.", NOT_FOUND, "B_001"),
}
