package kr.co.vacgom.api.carehistory.presentation.dto

import kr.co.vacgom.api.carehistory.domain.enums.BreastDirection
import java.time.LocalDate
import java.time.LocalDateTime

class BreastFeedingDto {
    data class Request(
        val recordDate: LocalDate,
        val startDate: LocalDateTime,
        val endDate: LocalDateTime,
        val breastDirection: BreastDirection
    )
}
