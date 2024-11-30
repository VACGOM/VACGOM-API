package kr.co.vacgom.api.user.presentation

import kr.co.vacgom.api.auth.security.util.SecurityContextUtil
import kr.co.vacgom.api.global.presentation.GlobalPath.BASE_V3
import kr.co.vacgom.api.user.application.UserService
import kr.co.vacgom.api.user.presentation.UserPath.USER
import kr.co.vacgom.api.user.presentation.dto.Signup
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(BASE_V3 + USER)
class UserController(
    private val userService: UserService,
) {
    @PostMapping
    fun signup(@RequestBody request: Signup.Request): Signup.Response {
        return userService.signup(request)
    }

    @DeleteMapping
    fun revoke() {
        val userId: UUID = SecurityContextUtil.getPrincipal()
        userService.revoke(userId)
    }
}
