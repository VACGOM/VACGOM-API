package kr.co.vacgom.api.carehistory.presentation.dto

import kr.co.vacgom.api.carehistory.domain.enums.ExcrementType
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class DiaperDto {
    data class Request(
        val babyId: UUID,
        val recordDate: LocalDate,
        val excrementType: ExcrementType,
        val executionDate: LocalDateTime,
    )
}

