package kr.co.vacgom.api

import kr.co.vacgom.api.global.presentation.GlobalPath.BASE_V3
import kr.co.vacgom.api.user.application.UserTokenService
import kr.co.vacgom.api.user.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(BASE_V3 + "/TEST")
class TestController(
    private val userRepository: UserRepository,
    private val userTokenService: UserTokenService
) {

    @PostMapping
    fun test(): ResponseEntity<String> {
        val user = userRepository.findAll().get(0)
        val accessToken = userTokenService.createAccessToken(user.id, user.role)
        return ResponseEntity.ok(accessToken)
    }
}
