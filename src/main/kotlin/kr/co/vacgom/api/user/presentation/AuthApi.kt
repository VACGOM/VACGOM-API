package kr.co.vacgom.api.user.presentation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import kr.co.vacgom.api.global.common.dto.BaseResponse
import kr.co.vacgom.api.global.exception.error.ErrorResponse
import kr.co.vacgom.api.user.presentation.dto.LoginDto
import kr.co.vacgom.api.user.presentation.dto.TokenDto

@Tag(name = "인증 API")
interface AuthApi {
    @Operation(
        summary = "로그아웃 API",
        operationId = "logout",
        description = """""",
        responses = [
            ApiResponse(responseCode = "200", description = "OK"),
            ApiResponse(responseCode = "400", description = "Bad Request", content = [Content(schema = Schema(implementation = ErrorResponse::class))]),
        ]
    )
    fun logout()

    @Operation(
        summary = "엑세스 토큰 재발급 API",
        operationId = "reIssueAccessToken",
        description = """
            헤더에 refresh Token 필요
        """,
        responses = [
            ApiResponse(responseCode = "200", description = "OK", content = [Content(schema = Schema(implementation = TokenDto.Response.Access::class))]),
            ApiResponse(responseCode = "400", description = "Bad Request", content = [Content(schema = Schema(implementation = ErrorResponse::class))]),
        ]
    )
    fun reIssueAccessToken(request: HttpServletRequest): BaseResponse<TokenDto.Response.Access>

    @Operation(
        summary = "소셜 로그인 API",
        operationId = "socialLogin",
        description = """
            엑세스 토큰 필요 X
        """,
        responses = [
            ApiResponse(responseCode = "200", description = "로그인 성공 시", content = [Content(schema = Schema(implementation = LoginDto.Response.Success::class))]),
            ApiResponse(responseCode = "201", description = "회원 가입 필요한 경우", content = [Content(schema = Schema(implementation = LoginDto.Response.Register::class))]),
            ApiResponse(responseCode = "400", description = "Bad Request", content = [Content(schema = Schema(implementation = ErrorResponse::class))]),
        ]
    )
    fun socialLogin(@Parameter(description = "소셜 로그인 Provider", required = true) provider: String,
                    @Parameter(description = "요청 body", required = true) request: LoginDto.Request.Social
    ): BaseResponse<LoginDto.Response>

    companion object {
        const val AUTH = "/auth"
    }
}
