package kr.co.vacgom.api.user.presentation

import jakarta.servlet.http.HttpServletRequest
import kr.co.vacgom.api.auth.jwt.exception.JwtError
import kr.co.vacgom.api.global.exception.error.BusinessException
import kr.co.vacgom.api.global.presentation.GlobalPath.BASE_V3
import kr.co.vacgom.api.user.application.AuthService
import kr.co.vacgom.api.user.application.UserTokenService
import kr.co.vacgom.api.user.presentation.AuthPath.AUTH
import kr.co.vacgom.api.user.presentation.dto.Login
import kr.co.vacgom.api.user.presentation.dto.Token
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(BASE_V3 + AUTH)
class AuthController(
    private val authService: AuthService,
    private val userTokenService: UserTokenService,
) {
    @PostMapping("/login/{provider}")
    fun socialLogin(@PathVariable provider: String,
                    @RequestBody request: Login.Request.Social
    ): Login.Response {
        return authService.socialLogin(provider, request)
    }

    @PostMapping("/logout")
    fun logout() {
        authService.logout()
    }

    @PatchMapping("/reissue")
    fun reIssueAccessToken(request: HttpServletRequest): Token.Response.Access {
        val refreshToken = userTokenService.extractToken(request)
            ?: throw BusinessException(JwtError.MALFORMED_JWT)

        return Token.Response.Access(
            userTokenService.reIssueAccessToken(refreshToken),
        )
    }
}
