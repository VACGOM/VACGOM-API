package kr.co.vacgom.api.baby.presentation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import kr.co.vacgom.api.baby.presentation.dto.BabyDto
import kr.co.vacgom.api.global.common.dto.BaseResponse
import kr.co.vacgom.api.global.exception.error.ErrorResponse
import java.util.*

@Tag(name = "아기 API")
interface BabyApi {
    @Operation(
        summary = "아기 프로필 이미지 업로드 API",
        operationId = "uploadBabyImage",
        description = """""",
        responses = [
            ApiResponse(responseCode = "200", description = "OK"),
            ApiResponse(
                responseCode = "400",
                description = "Bad Request",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
        ]
    )
    fun uploadBabyImage(
        @Parameter(description = "이미지 바이너리 데이터")
        request: BabyDto.Request.UploadImage
    ): BaseResponse<List<BabyDto.Response.UploadedImage>>

    @Operation(
        summary = "아기 상세정보(나이 포함 여부 선택 가능) 조회 API",
        operationId = "getBabyDetail",
        description = """""",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "OK",
                content = [Content(schema = Schema(
                    oneOf = [BabyDto.Response.Detail::class, BabyDto.Response.DetailWithAge::class],
                    exampleClasses = [BabyDto.Response.Detail::class, BabyDto.Response.DetailWithAge::class])
                )]
            ),
            ApiResponse(
                responseCode = "400",
                description = "Bad Request",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
        ]
    )
    fun getBabyDetail(babyId: UUID, withAge: Boolean?): BaseResponse<BabyDto.Response>

    @Operation(
        summary = "유저 돌봄 아이 리스트 조회 API",
        operationId = "getUserBabyDetails",
        description = """""",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "OK",
                content = [Content(schema = Schema(implementation = BabyDto.Response.DetailWithAge::class))]
            ),
            ApiResponse(
                responseCode = "400",
                description = "Bad Request",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
        ]
    )
    fun getUserBabyDetailsWithAge(): BaseResponse<List<BabyDto.Response.DetailWithAge>>

    @Operation(
        summary = "유저 돌봄 아이 추가 API",
        operationId = "createBaby",
        description = """""",
        responses = [
            ApiResponse(responseCode = "200", description = "OK"),
            ApiResponse(
                responseCode = "400",
                description = "Bad Request",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
        ]
    )
    fun createBaby(request: BabyDto.Request.Create)

    @Operation(
        summary = "아기 정보 업데이트 API",
        operationId = "updateBaby",
        description = """""",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "OK",
                content = [Content(schema = Schema(implementation = BabyDto.Response.Detail::class))]
            ),
            ApiResponse(
                responseCode = "400",
                description = "Bad Request",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
        ]
    )
    fun updateBaby(babyId: UUID, request: BabyDto.Request.Update): BaseResponse<BabyDto.Response.Detail>

    companion object {
        const val BABY = "/babies"
    }
}
