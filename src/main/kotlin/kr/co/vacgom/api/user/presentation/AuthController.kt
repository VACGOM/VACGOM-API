package kr.co.vacgom.api.user.presentation

import jakarta.servlet.http.HttpServletRequest
import kr.co.vacgom.api.auth.jwt.JwtProvider
import kr.co.vacgom.api.auth.jwt.exception.JwtError
import kr.co.vacgom.api.global.common.dto.BaseResponse
import kr.co.vacgom.api.global.exception.error.BusinessException
import kr.co.vacgom.api.global.presentation.GlobalPath.BASE_V3
import kr.co.vacgom.api.user.application.AuthService
import kr.co.vacgom.api.user.application.UserTokenService
import kr.co.vacgom.api.user.presentation.AuthApi.Companion.AUTH
import kr.co.vacgom.api.user.presentation.dto.LoginDto
import kr.co.vacgom.api.user.presentation.dto.TokenDto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(BASE_V3 + AUTH)
class AuthController(
    private val authService: AuthService,
    private val userTokenService: UserTokenService,
    private val jwtProvider: JwtProvider,
): AuthApi {
    @PostMapping("/login/{provider}")
    override fun socialLogin(
        @PathVariable provider: String,
        @RequestBody request: LoginDto.Request.Social
    ): BaseResponse<LoginDto.Response> {
        return authService.socialLogin(provider, request).let { BaseResponse.success(it) }
    }

    @PostMapping("/logout")
    override fun logout() {
        authService.logout()
    }

    @PatchMapping("/reissue")
    override fun reIssueAccessToken(request: HttpServletRequest): BaseResponse<TokenDto.Response.Access> {
        val refreshToken = jwtProvider.extractToken(request)
            ?: throw BusinessException(JwtError.MALFORMED_JWT)

        return userTokenService.reIssueAccessToken(refreshToken).let { BaseResponse.success(it) }
    }
}
