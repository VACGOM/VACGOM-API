package kr.co.vacgom.api.member.presentation

import jakarta.servlet.http.HttpServletRequest
import kr.co.vacgom.api.global.exception.error.BusinessException
import kr.co.vacgom.api.global.exception.error.GlobalError
import kr.co.vacgom.api.global.presentation.GlobalPath.BASE_V3
import kr.co.vacgom.api.member.presentation.AuthPath.AUTH
import kr.co.vacgom.api.member.presentation.dto.Login
import kr.co.vacgom.api.member.presentation.dto.Token
import kr.co.vacgom.api.member.service.AuthService
import kr.co.vacgom.api.member.service.MemberTokenService
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
    fun localLogin(@RequestBody request: Login.Request.Local): Login.Response {
        return authService.localLogin(request)
    }

    @PostMapping("/logout")
    fun logout() {
        authService.logout()
    }

    @PostMapping("/reissue")
    fun reIssueAccessToken(request: HttpServletRequest): Token.Response.Access {
        val refreshToken = memberTokenService.extractToken(request)
            ?: throw BusinessException(GlobalError.INVALID_REQUEST_PARAM)

        return Token.Response.Access(
            memberTokenService.reIssueAccessToken(refreshToken),
        )
    }
}
