package kr.co.vacgom.api.user.presentation

import kr.co.vacgom.api.global.presentation.GlobalPath.BASE_V3
import kr.co.vacgom.api.global.security.util.SecurityContextUtil
import kr.co.vacgom.api.user.application.UserService
import kr.co.vacgom.api.user.presentation.UserPath.USER
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(BASE_V3 + USER)
class UserController(
    private val userService: UserService,
) {
    @PostMapping
    fun signup() {
        return userService.signup()
    }

    @DeleteMapping
    fun revoke() {
        val userId: Long = SecurityContextUtil.getPrincipal()
        userService.revoke(userId)
    }
}
