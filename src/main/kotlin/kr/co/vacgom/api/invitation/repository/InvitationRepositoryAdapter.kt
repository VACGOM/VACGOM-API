package kr.co.vacgom.api.invitation.repository

import kr.co.vacgom.api.invitation.domain.InvitationCode
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class InvitationRepositoryAdapter(
    private val invitationRedisRepository: InvitationRedisRepository
): InvitationRepository {
    override fun saveInvitationCode(invitationCode: InvitationCode) {
        invitationRedisRepository.save(invitationCode)
    }

    override fun getAndDeleteInvitationCode(code: UUID) {
    }
}
