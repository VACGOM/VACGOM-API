package kr.co.vacgom.api.carehistory.domain

import kr.co.vacgom.api.carehistory.domain.enums.ExcrementType
import kr.co.vacgom.api.global.common.domain.BaseTimeEntity
import java.time.LocalDateTime

data class Diaper (
    val excrementType: ExcrementType,
    val executionDate: LocalDateTime,
): BaseTimeEntity() {
    companion object {
        fun create(executionDate: LocalDateTime, excrementType: ExcrementType): Diaper {
            return Diaper(excrementType = excrementType, executionDate = executionDate)
        }
    }
}
