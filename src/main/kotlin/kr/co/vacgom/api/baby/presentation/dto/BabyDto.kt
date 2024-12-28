package kr.co.vacgom.api.baby.presentation.dto

import kr.co.vacgom.api.baby.domain.enums.Gender
import java.time.LocalDate
import java.util.*

class BabyDto {
    class Response {
        data class Detail(
            val id: UUID,
            val name: String,
            val profileImg: String?,
            val gender: Gender,
            val birthday: LocalDate,
        )
    }
}
