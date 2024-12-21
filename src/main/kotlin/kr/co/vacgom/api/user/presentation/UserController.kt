package kr.co.vacgom.api.user.presentation

import kr.co.vacgom.api.auth.security.util.SecurityContextUtil
import kr.co.vacgom.api.global.common.dto.BaseResponse
import kr.co.vacgom.api.global.presentation.GlobalPath.BASE_V3
import kr.co.vacgom.api.user.application.UserService
import kr.co.vacgom.api.user.presentation.UserApi.Companion.USER
import kr.co.vacgom.api.user.presentation.dto.SignupDto
import kr.co.vacgom.api.user.presentation.dto.UserDto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(BASE_V3 + USER)
class UserController(
    private val userService: UserService,
): UserApi {
    @GetMapping
    override fun getUserDetail(): BaseResponse<UserDto.Response.UserDetail> {
        val userId = SecurityContextUtil.getPrincipal()
        return BaseResponse.success(userService.getUserDetail(userId))
    }

    @PostMapping
    override fun signup(@RequestBody request: SignupDto.Request): BaseResponse<SignupDto.Response> {
        return BaseResponse.success(userService.signup(request))
    }

    @DeleteMapping
    override fun revoke() {
        val userId = SecurityContextUtil.getPrincipal()
        userService.revoke(userId)
    }
}
