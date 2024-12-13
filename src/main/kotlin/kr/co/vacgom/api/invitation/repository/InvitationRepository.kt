package kr.co.vacgom.api.invitation.repository

import kr.co.vacgom.api.invitation.domain.InvitationCode
import java.util.*

interface InvitationRepository {
    fun saveInvitationCode(invitationCode: InvitationCode)
    fun getAndDeleteInvitationCode(code: UUID)
}
