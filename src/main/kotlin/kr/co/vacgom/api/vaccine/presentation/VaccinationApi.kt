package kr.co.vacgom.api.vaccine.presentation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import kr.co.vacgom.api.global.exception.error.ErrorResponse
import kr.co.vacgom.api.vaccine.presentation.dto.VaccinationDto

@Tag(name = "백신 접종내역 API")
interface VaccinationApi {
    @Operation(
        summary = "백신 접종 내역 등록",
        operationId = "createVaccinations",
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
    fun createVaccinations(request: List<VaccinationDto.Request.Create>)

    companion object {
        const val VACCINATIONS = "/vaccinations"
    }
}
