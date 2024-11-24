package kr.co.vacgom.api.carehistory.presentation.dto

import java.time.LocalDate
import java.time.LocalDateTime

class SnackDto {
    data class Request(
        val recordDate: LocalDate,
        val memo: String,
        val executionDate: LocalDateTime,
    )
}
