package kr.co.vacgom.api.carehistory.presentation.dto

import kr.co.vacgom.api.carehistory.domain.enums.ExcrementType
import java.time.LocalDate
import java.time.LocalDateTime

class DiaperDto {
    data class Request(
        val recordDate: LocalDate,
        val excrementType: ExcrementType,
        val executionDate: LocalDateTime,
    )
}

