package kr.co.vacgom.api.carehistory.presentation.dto

import kr.co.vacgom.api.carehistory.domain.enums.BreastDirection
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class BreastFeedingDto {
    data class Request(
        val babyId: UUID,
        val recordDate: LocalDate,
        val startDate: LocalDateTime,
        val endDate: LocalDateTime,
        val breastDirection: BreastDirection
    )
}
