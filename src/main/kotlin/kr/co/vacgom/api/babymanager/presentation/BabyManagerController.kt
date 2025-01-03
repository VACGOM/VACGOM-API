package kr.co.vacgom.api.babymanager.presentation

import kr.co.vacgom.api.auth.security.util.SecurityContextUtil
import kr.co.vacgom.api.babymanager.application.BabyManagerService
import kr.co.vacgom.api.babymanager.presentation.BabyManagerApi.Companion.BABY_MANAGER
import kr.co.vacgom.api.babymanager.presentation.dto.BabyManagerDto
import kr.co.vacgom.api.global.presentation.GlobalPath.BASE_V3
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(BASE_V3 + BABY_MANAGER)
class BabyManagerController(
    private val babyManagerService: BabyManagerService
): BabyManagerApi {
    @DeleteMapping
    override fun deleteBabyManager(@RequestBody request: BabyManagerDto.Request.Delete) {
        val userId = SecurityContextUtil.getPrincipal()
        babyManagerService.deleteBabyManager(userId, request.babyId, request.managerId)
    }
}
