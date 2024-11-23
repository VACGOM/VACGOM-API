package kr.co.vacgom.api.carehistory.domain

import kr.co.vacgom.api.global.common.domain.BaseTimeEntity
import java.time.LocalDateTime

data class Snack (
    val memo: String,
    val executionDate: LocalDateTime,
): BaseTimeEntity() {
    companion object {
        fun create(memo: String, executionDate: LocalDateTime): Snack {
            return Snack(memo = memo, executionDate = executionDate)
        }
    }
}
