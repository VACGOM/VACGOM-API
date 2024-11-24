package kr.co.vacgom.api.carehistory.presentation.dto

import java.time.LocalDate
import java.time.LocalDateTime

class BabyFormulaDto {
    data class Request(
        val recordDate: LocalDate,
        val amount: Long,
        val executionDate: LocalDateTime,
    )
}
