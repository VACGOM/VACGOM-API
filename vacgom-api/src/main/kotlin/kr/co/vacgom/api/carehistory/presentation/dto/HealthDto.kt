package kr.co.vacgom.api.carehistory.presentation.dto

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class HealthDto {
    data class Request(
        val babyId: UUID,
        val recordDate: LocalDate,
        val temperature: Double,
        val memo: String,
        val executionDate: LocalDateTime,
    )
}
