package kr.co.vacgom.api.user.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema

class LoginDto {
    sealed class Request {
        @Schema(name = "LoginDto.Request.Social")
        data class Social(
            val accessToken: String,
        )
    }

    sealed class Response {
        @Schema(name = "LoginDto.Response.Success")
        data class Success(
            val accessToken: String,
            val refreshToken: String,
        ): Response()

        @Schema(name = "LoginDto.Response.Register")
        data class Register(
            val registerToken: String,
        ): Response()
    }
}
