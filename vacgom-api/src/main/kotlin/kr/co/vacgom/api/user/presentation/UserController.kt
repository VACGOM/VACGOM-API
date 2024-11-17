package kr.co.vacgom.api.user.presentation

import kr.co.vacgom.api.global.presentation.GlobalPath.BASE_V3
import kr.co.vacgom.api.global.security.util.SecurityContextUtil
import kr.co.vacgom.api.user.application.UserService
import kr.co.vacgom.api.user.presentation.UserPath.MEMBER
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(BASE_V3 + MEMBER)
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
