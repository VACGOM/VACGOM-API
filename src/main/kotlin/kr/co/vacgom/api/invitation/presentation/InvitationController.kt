package kr.co.vacgom.api.invitation.presentation

import kr.co.vacgom.api.auth.security.util.SecurityContextUtil
import kr.co.vacgom.api.baby.presentation.dto.BabyDto
import kr.co.vacgom.api.global.common.dto.BaseResponse
import kr.co.vacgom.api.global.presentation.GlobalPath.BASE_V3
import kr.co.vacgom.api.invitation.application.InvitationService
import kr.co.vacgom.api.invitation.presentation.InvitationApi.Companion.INVITATION
import kr.co.vacgom.api.invitation.presentation.dto.InvitationDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(BASE_V3 + INVITATION)
class InvitationController(
    private val invitationService: InvitationService
): InvitationApi {
    @PostMapping("/my")
    override fun createInvitationCode(@RequestBody request: InvitationDto.Request.Create): BaseResponse<InvitationDto.Response.Create> {
        val userId = SecurityContextUtil.getPrincipal()

        return BaseResponse.success {
            invitationService.createInvitationCode(userId, request.careScope)
        }
    }

    @PostMapping
    override fun getBabiesByInvitationCode(@RequestBody request: InvitationDto.Request.Get): List<BabyDto.Response.Detail> {
        val userId = SecurityContextUtil.getPrincipal()
        return invitationService.getBabiesByInvitationCode(userId, request.invitationCode)
    }
}
