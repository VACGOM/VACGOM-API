package kr.co.vacgom.api.user.presentation

import jakarta.servlet.http.HttpServletRequest
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
): AuthApi {
    @PostMapping("/login/{provider}")
    override fun socialLogin(@PathVariable provider: String,
                    @RequestBody request: LoginDto.Request.Social
    ): BaseResponse<LoginDto.Response> {
        return BaseResponse.success{
            authService.socialLogin(provider, request)
        }
    }

    @PostMapping("/logout")
    override fun logout() {
        authService.logout()
    }

    @PatchMapping("/reissue")
    override fun reIssueAccessToken(request: HttpServletRequest): BaseResponse<TokenDto.Response.Access> {
        val refreshToken = userTokenService.extractToken(request)
            ?: throw BusinessException(JwtError.MALFORMED_JWT)

        return BaseResponse.success(TokenDto.Response.Access(
            userTokenService.reIssueAccessToken(refreshToken),
        ))
    }
}
