package kr.co.vacgom.api.invitation.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

class InvitationDto {
    class Request {
        @Schema(name = "InvitationDto.Request.Create")
        data class Create(val babyId: UUID?)

        @Schema(name = "InvitationDto.Request.Get")
        data class Get(val invitationCode: String)
    }

    class Response {
        @Schema(name = "InvitationDto.Response.Create")
        data class Create(val invitationCode: String)
    }
}
