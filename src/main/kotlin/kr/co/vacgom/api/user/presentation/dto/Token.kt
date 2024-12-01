package kr.co.vacgom.api.user.presentation.dto

class Token {
    data class Response(
        val accessToken: String,
        val refreshToken: String,
    ) {
        data class Access(
            val accessToken: String,
        )
    }
}
