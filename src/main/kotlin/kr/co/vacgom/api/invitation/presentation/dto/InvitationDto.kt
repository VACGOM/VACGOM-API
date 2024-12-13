package kr.co.vacgom.api.invitation.presentation.dto

import kr.co.vacgom.api.invitation.domain.CareScope
import java.util.*

class InvitationDto {
    class Request {
        data class Create(val careScope: CareScope)
        data class Register(val invitationCode: String)
    }

    class Response {
        data class Create(val invitationCode: UUID)
    }
}
