package kr.co.vacgom.api.carehistory.domain

import kr.co.vacgom.api.global.common.domain.BaseTimeEntity
import java.time.LocalDateTime

data class BabyFormula (
    val amount: Long,
    val executionDate: LocalDateTime,
): BaseTimeEntity() {
    companion object {
        fun create(amount: Long, executionDate: LocalDateTime): BabyFormula {
            return BabyFormula(amount = amount, executionDate = executionDate)
        }
    }
}
