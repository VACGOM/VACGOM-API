package kr.co.vacgom.api.user.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema

class TokenDto {
    @Schema(name = "TokenDto.Response")
    data class Response(
        val accessToken: String,
        val refreshToken: String,
    ) {
        @Schema(name = "TokenDto.Response.Access")
        data class Access(
            val accessToken: String,
        )
    }
}
