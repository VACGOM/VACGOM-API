package kr.co.vacgom.api.user.presentation.dto

class Login {
    sealed class Request {
        data class Social(
            val accessToken: String,
        )
    }

    sealed class Response {
        data class Success(
            val accessToken: String,
            val refreshToken: String,
        ): Response()

        data class Register(
            val registerToken: String,
        ): Response()
    }
}

