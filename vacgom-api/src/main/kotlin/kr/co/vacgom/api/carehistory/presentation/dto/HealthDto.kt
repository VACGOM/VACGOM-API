package kr.co.vacgom.api.carehistory.presentation.dto

import java.time.LocalDate
import java.time.LocalDateTime

class HealthDto {
    data class Request(
        val recordDate: LocalDate,
        val temperature: Double,
        val memo: String,
        val executionDate: LocalDateTime,
    )
}
