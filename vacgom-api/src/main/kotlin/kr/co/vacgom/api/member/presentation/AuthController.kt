package kr.co.vacgom.api.member.presentation

import jakarta.servlet.http.HttpServletRequest
import kr.co.vacgom.api.auth.jwt.exception.JwtError
import kr.co.vacgom.api.global.exception.error.BusinessException
import kr.co.vacgom.api.global.presentation.GlobalPath.BASE_V3
import kr.co.vacgom.api.member.presentation.AuthPath.AUTH
import kr.co.vacgom.api.member.presentation.dto.Login
import kr.co.vacgom.api.member.presentation.dto.Token
import kr.co.vacgom.api.member.application.AuthService
import kr.co.vacgom.api.member.application.MemberTokenService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(BASE_V3 + AUTH)
class AuthController(
    private val authService: AuthService,
    private val memberTokenService: MemberTokenService,
) {
    @PostMapping("/login/{provider}")
    fun socialLogin(@PathVariable provider: String,
                    @RequestBody request: Login.Request.Social
    ): Login.Response {
        return authService.socialLogin(provider, request)
    }

    @PostMapping("/login")
    fun localLogin(@RequestBody request: Login.Request.Local): Login.Response.Success {
        return authService.localLogin(request)
    }

    @PostMapping("/logout")
    fun logout() {
        authService.logout()
    }

    @PostMapping("/reissue")
    fun reIssueAccessToken(request: HttpServletRequest): Token.Response.Access {
        val refreshToken = memberTokenService.extractToken(request)
            ?: throw BusinessException(JwtError.MALFORMED_JWT)

        return Token.Response.Access(
            memberTokenService.reIssueAccessToken(refreshToken),
        )
    }
}
