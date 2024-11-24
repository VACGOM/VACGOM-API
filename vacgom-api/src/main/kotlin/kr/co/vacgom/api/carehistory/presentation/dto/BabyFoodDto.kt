package kr.co.vacgom.api.carehistory.presentation.dto

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class BabyFoodDto {
    data class Request(
        val babyId: UUID,
        val recordDate: LocalDate,
        val amount: Long,
        val executionDate: LocalDateTime,
    )
}
