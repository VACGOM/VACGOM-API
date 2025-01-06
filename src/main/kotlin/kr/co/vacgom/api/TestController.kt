package kr.co.vacgom.api

import kr.co.vacgom.api.auth.oauth.enums.SocialLoginProvider
import kr.co.vacgom.api.global.presentation.GlobalPath.BASE_V3
import kr.co.vacgom.api.user.application.UserTokenService
import kr.co.vacgom.api.user.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import kotlin.random.Random

@RestController
@RequestMapping(BASE_V3 + "/TEST")
class TestController(
    private val userRepository: UserRepository,
    private val userTokenService: UserTokenService
) {

    @PostMapping
    fun test(@RequestParam tokenType: String): ResponseEntity<String> {
        val user = userRepository.findAll()[0]

        when (tokenType) {
            "ACCESS" -> {
                val accessToken = userTokenService.createAccessToken(user.id, user.role)
                return ResponseEntity.ok(accessToken)
            }
            "REFRESH" -> {
                val refreshToken = userTokenService.createRefreshToken(user.id)
                return ResponseEntity.ok(refreshToken)
            }
            "REGISTER" -> {
                val socialId = "testSocialId${Random(System.currentTimeMillis()).nextInt()}"
                val registerToken = userTokenService.createRegisterToken(socialId, SocialLoginProvider.KAKAO.name)
                return ResponseEntity.ok(registerToken)
            }
        }

        return ResponseEntity.internalServerError().build()
    }
}
