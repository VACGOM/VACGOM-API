package kr.co.vacgom.api.invitation.application

import kr.co.vacgom.api.babymanager.application.BabyManagerService
import kr.co.vacgom.api.global.util.UuidCreator
import kr.co.vacgom.api.invitation.domain.CareScope
import kr.co.vacgom.api.invitation.domain.InvitationCode
import kr.co.vacgom.api.invitation.presentation.dto.InvitationDto
import kr.co.vacgom.api.invitation.repository.InvitationRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class InvitationService(
    private val invitationRepository: InvitationRepository,
    private val babyManagerService: BabyManagerService,
) {
    fun createInvitationCode(userId: UUID, careScope: CareScope): InvitationDto.Response.Create {
        return InvitationCode(
            code = UuidCreator.create(),
            babyIds = babyManagerService.getBabiesByUserIsAdmin(userId).map { it.id }.toSet()
        )
        .also { invitationRepository.saveInvitationCode(it) }
        .let { InvitationDto.Response.Create(invitationCode = it.code) }
    }
}
