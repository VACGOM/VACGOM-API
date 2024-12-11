package kr.co.vacgom.api.user.presentation.dto

import kr.co.vacgom.api.user.domain.enums.Gender
import java.time.LocalDate
import java.time.LocalDateTime

class Signup {
    data class Request(
        val registerToken: String,
        val nickname: String,
        val babies: List<Baby>,
    ) {
        data class Baby(
            val name: String,
            val profileImgUrl: String,
            val gender: Gender,
            val birthday: LocalDate,
        )
    }

    data class Response(
        val accessToken: String,
        val refreshToken: String,
    )
}
