package kr.co.vacgom.api.vaccine.exception

import kr.co.vacgom.api.global.exception.error.ErrorCode
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.NOT_FOUND

enum class VaccinationError(
    override val message: String,
    override val status: HttpStatus,
    override val code: String,
) : ErrorCode {
    VACCINE_NOT_FOUND("해당 백신이 존재하지 않습니다.", NOT_FOUND, "V_001")
}
