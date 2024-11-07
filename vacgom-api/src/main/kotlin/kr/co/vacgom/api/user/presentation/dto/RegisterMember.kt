package kr.co.vacgom.api.user.presentation.dto

class RegisterMember {
    data class Request(
        val name: String,
        val id: String,
        val password: String,
    )

    class Response {
        data class Success(
            val accessToken: String,
            val refreshToken: String,
        )
    }
}

