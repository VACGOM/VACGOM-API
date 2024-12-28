package kr.co.vacgom.api.invitation.application

import kr.co.vacgom.api.baby.application.BabyService
import kr.co.vacgom.api.baby.presentation.dto.BabyDto
import kr.co.vacgom.api.babymanager.application.BabyManagerService
import kr.co.vacgom.api.global.exception.error.BusinessException
import kr.co.vacgom.api.global.util.UuidCreator
import kr.co.vacgom.api.invitation.domain.CareScope
import kr.co.vacgom.api.invitation.domain.InvitationCode
import kr.co.vacgom.api.invitation.exception.InvitationError
import kr.co.vacgom.api.invitation.presentation.dto.InvitationDto
import kr.co.vacgom.api.invitation.repository.InvitationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class InvitationService(
    private val invitationRepository: InvitationRepository,
    private val babyManagerService: BabyManagerService,
    private val babyService: BabyService,
) {
    @Transactional
    fun createInvitationCode(userId: UUID, careScope: CareScope): InvitationDto.Response.Create {
        val key = UuidCreator.create().toString()

        babyManagerService.getBabiesByUserIsAdmin(userId)
            .map { baby -> baby.id }
            .let { babyIds -> InvitationCode(key = key, userId = userId, babyIds = babyIds) }
            .also { invitationCode -> invitationRepository.save(invitationCode, invitationCode.timeToLive) }

        return InvitationDto.Response.Create(invitationCode = key)
    }

    @Transactional
    fun getBabiesByInvitationCode(userId: UUID, code: String): List<BabyDto.Response.Detail> {
        val invitationCode = invitationRepository.getAndDeleteInvitationCode(code)
            ?: throw BusinessException(InvitationError.INVITATION_CODE_NOT_FOUND)

        return babyService.getBabiesById(invitationCode.babyIds).map {
            BabyDto.Response.Detail(
                id = it.id,
                name = it.name,
                profileImg = it.profileImg,
                gender = it.gender,
                birthday = it.birthday,
            )
        }
    }
}
