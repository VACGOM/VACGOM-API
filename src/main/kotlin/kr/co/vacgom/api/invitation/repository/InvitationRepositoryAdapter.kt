package kr.co.vacgom.api.invitation.repository

import kr.co.vacgom.api.invitation.domain.InvitationCode
import org.springframework.stereotype.Repository

@Repository
class InvitationRepositoryAdapter(
    private val invitationRedisRepository: InvitationRedisRepository,
): InvitationRepository {
    override fun save(invitationCode: InvitationCode, ttl: Long) {
        invitationRedisRepository.save(invitationCode, ttl)
    }

    override fun getAndDeleteInvitationCode(code: String): InvitationCode? {
        return invitationRedisRepository.getAndDeleteInvitationCode(code)
    }
}
