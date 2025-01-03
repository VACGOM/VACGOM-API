package kr.co.vacgom.api.user.presentation

import kr.co.vacgom.api.auth.security.util.SecurityContextUtil
import kr.co.vacgom.api.global.common.dto.BaseResponse
import kr.co.vacgom.api.global.presentation.GlobalPath.BASE_V3
import kr.co.vacgom.api.user.application.UserCommandService
import kr.co.vacgom.api.user.application.UserQueryService
import kr.co.vacgom.api.user.presentation.UserApi.Companion.USER
import kr.co.vacgom.api.user.presentation.dto.SignupDto
import kr.co.vacgom.api.user.presentation.dto.UserDto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(BASE_V3 + USER)
class UserController(
    private val userCommandService: UserCommandService,
    private val userQueryService: UserQueryService,
) : UserApi {
    @GetMapping
    override fun getUserDetail(): BaseResponse<UserDto.Response.UserDetail> {
        val userId = SecurityContextUtil.getPrincipal()
        return userQueryService.getUserDetail(userId).let { BaseResponse.success(it) }
    }

    @PostMapping
    override fun signup(@RequestBody request: SignupDto.Request): BaseResponse<SignupDto.Response> {
        return userCommandService.signup(request).let { BaseResponse.success(it) }
    }

    @DeleteMapping
    override fun revoke() {
        val userId = SecurityContextUtil.getPrincipal()
        userCommandService.revoke(userId)
    }
}
