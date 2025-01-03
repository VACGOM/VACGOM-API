package kr.co.vacgom.api.user.presentation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import kr.co.vacgom.api.global.common.dto.BaseResponse
import kr.co.vacgom.api.global.exception.error.ErrorResponse
import kr.co.vacgom.api.user.presentation.dto.SignupDto
import kr.co.vacgom.api.user.presentation.dto.UserDto

@Tag(name = "유저 API",)
interface UserApi {
    @Operation(
        summary = "회원 탈퇴 API",
        operationId = "revoke",
        description = """""",
        responses = [
            ApiResponse(responseCode = "200", description = "OK"),
            ApiResponse(responseCode = "400", description = "Bad Request", content = [Content(schema = Schema(implementation = ErrorResponse::class))]),
        ]
    )
    fun revoke()

    @Operation(
        summary = "유저 상세정보 조회 API",
        operationId = "getUserDetail",
        description = """""",
        responses = [
            ApiResponse(responseCode = "200", description = "OK", content = [Content(schema = Schema(implementation = UserDto.Response.UserDetail::class))]),
            ApiResponse(responseCode = "400", description = "Bad Request", content = [Content(schema = Schema(implementation = ErrorResponse::class))]),
        ]
    )
    fun getUserDetail(): BaseResponse<UserDto.Response.UserDetail>

    @Operation(
        summary = "회원 가입 API",
        operationId = "signup",
        description = """
            엑세스 토큰 필요 X
        """,
        responses = [
            ApiResponse(responseCode = "200", description = "OK", content = [Content(schema = Schema(implementation = SignupDto.Response::class))]),
            ApiResponse(responseCode = "400", description = "Bad Request", content = [Content(schema = Schema(implementation = ErrorResponse::class))]),
        ]
    )
    fun signup(
        @Parameter(description = "", required = true) request: SignupDto.Request,
    ): BaseResponse<SignupDto.Response>

    @Operation(
        summary = "회원 가입(초대코드) API",
        operationId = "signupByInvitationCode",
        description = """
            엑세스 토큰 필요 X
        """,
        responses = [
            ApiResponse(responseCode = "200", description = "OK", content = [Content(schema = Schema(implementation = SignupDto.Response::class))]),
            ApiResponse(responseCode = "400", description = "Bad Request", content = [Content(schema = Schema(implementation = ErrorResponse::class))]),
        ]
    )
    fun signupByInvitationCode(
        @Parameter(description = "", required = true) request: SignupDto.Request.Invitation,
    ): BaseResponse<SignupDto.Response>

    companion object {
        const val USER = "/users"
    }
}
