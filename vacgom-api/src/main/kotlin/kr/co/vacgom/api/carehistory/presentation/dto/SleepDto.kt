package kr.co.vacgom.api.carehistory.presentation.dto

import java.time.LocalDate
import java.time.LocalDateTime

class SleepDto {
    data class Request(
        val recordDate: LocalDate,
        val startDate: LocalDateTime,
        val endDate: LocalDateTime,
    )
}
