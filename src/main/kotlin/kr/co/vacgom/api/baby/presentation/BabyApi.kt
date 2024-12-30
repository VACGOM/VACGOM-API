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

    companion object {
        const val BABY = "/babies"
    }
}
