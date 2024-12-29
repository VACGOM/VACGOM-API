package kr.co.vacgom.api.babymanager.exception

import kr.co.vacgom.api.global.exception.error.ErrorCode
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.NOT_FOUND

enum class BabyManagerError(
    override val message: String,
    override val status: HttpStatus,
    override val code: String,
) : ErrorCode {
    BABY_MANAGER_NOT_FOUND("아기 돌보미가 존재하지 않습니다.", NOT_FOUND, "BM_001"),
    NOT_ADMIN_BABY_MANAGER("아기의 대표 돌보미가 아닙니다.", NOT_FOUND, "BM_002"),
}
