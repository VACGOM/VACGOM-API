package kr.co.vacgom.api.invitation.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.vacgom.api.invitation.domain.CareScope

class InvitationDto {
    class Request {
        @Schema(name = "InvitationDto.Request.Create")
        data class Create(val careScope: CareScope)
        @Schema(name = "InvitationDto.Request.Register")
        data class Register(val invitationCode: String)
    }

    class Response {
        @Schema(name = "InvitationDto.Response.Create")
        data class Create(val invitationCode: String)
    }
}
