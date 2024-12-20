package kr.co.vacgom.api.invitation.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.vacgom.api.invitation.domain.CareScope

class InvitationDto {
    @Schema(name = "InvitationDto.Request")
    class Request {
        data class Create(val careScope: CareScope)
        data class Register(val invitationCode: String)
    }

    @Schema(name = "InvitationDto.Response")
    class Response {
        data class Create(val invitationCode: String)
    }
}
