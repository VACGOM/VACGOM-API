package kr.co.vacgom.api.carehistory.presentation.dto

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class SnackDto {
    data class Request(
        val babyId: UUID,
        val recordDate: LocalDate,
        val memo: String,
        val executionDate: LocalDateTime,
    )
}
