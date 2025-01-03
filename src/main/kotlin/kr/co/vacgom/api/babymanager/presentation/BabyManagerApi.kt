package kr.co.vacgom.api.babymanager.presentation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import kr.co.vacgom.api.babymanager.presentation.dto.BabyManagerDto
import kr.co.vacgom.api.global.exception.error.ErrorResponse

@Tag(name = "아기 돌보미 API")
interface BabyManagerApi {
    @Operation(
        summary = "공동 돌보미 삭제 API",
        operationId = "deleteBabyManager",
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
    fun deleteBabyManager(request: BabyManagerDto.Request.Delete)

    companion object {
        const val BABY_MANAGER = "/baby-managers"
    }
}
