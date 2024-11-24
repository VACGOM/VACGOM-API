package kr.co.vacgom.api.carehistory.presentation.dto

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class SleepDto {
    data class Request(
        val babyId: UUID,
        val recordDate: LocalDate,
        val startDate: LocalDateTime,
        val endDate: LocalDateTime,
    )
}
