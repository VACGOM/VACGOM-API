package kr.co.vacgom.api.member.presentation.dto

class Login {
    sealed class Request {
        data class Social(
            val accessToken: String,
        )

        data class Local(
            val id: String,
            val password: String,
        )
    }

    data class Response(
        val accessToken: String,
        val refreshToken: String,
    )
}

