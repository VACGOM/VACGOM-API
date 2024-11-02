package kr.co.vacgom.api.member.presentation.dto

class Token {
    data class Request(
        val accessToken: String,
        val refreshToken: String,
    )

    data class Response(
        val accessToken: String,
        val refreshToken: String,
    ) {
        data class Access(
            val accessToken: String,
        )
    }
}
