package kr.co.vacgom.api.member.presentation

import kr.co.vacgom.api.global.presentation.GlobalPath.BASE_V3
import kr.co.vacgom.api.global.security.SecurityContextHolder
import kr.co.vacgom.api.member.presentation.MemberPath.MEMBER
import kr.co.vacgom.api.member.application.MemberService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(BASE_V3 + MEMBER)
class MemberController(
    private val memberService: MemberService,
) {
    @PostMapping
    fun signup() {
        return memberService.signup()
    }

    @DeleteMapping
    fun revoke() {
        val userId: Long = SecurityContextHolder.getAuthentication().userId
        memberService.revoke(userId)
    }
}
