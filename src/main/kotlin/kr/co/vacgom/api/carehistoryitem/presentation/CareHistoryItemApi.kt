package kr.co.vacgom.api.carehistoryitem.presentation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import kr.co.vacgom.api.carehistoryitem.presentation.dto.*
import kr.co.vacgom.api.global.common.dto.BaseResponse
import kr.co.vacgom.api.global.exception.error.ErrorResponse
import java.time.LocalDate
import java.util.*

@Tag(name = "육아 기록 API")
interface CareHistoryItemApi {
    @Operation(
        summary = "육아 기록 통계 조회(일간) API",
        operationId = "getCareHistoryByExecutionDate",
        description = """""",
        responses = [
            ApiResponse(responseCode = "200", description = "OK", content = [Content(schema = Schema(implementation = CareHistoryDto.Response.Daily::class))]),
            ApiResponse(responseCode = "400", description = "Bad Request", content = [Content(schema = Schema(implementation = ErrorResponse::class))]),
        ]
    )
    fun getCareHistoryByExecutionDate(
        babyId: UUID,
        executionDate: LocalDate,
    ): BaseResponse<CareHistoryDto.Response.Daily>

    @Operation(
        summary = "모유 수유 기록 추가 API",
        operationId = "addBreastFeeding",
        description = """""",
        responses = [
            ApiResponse(responseCode = "200", description = "OK"),
            ApiResponse(responseCode = "400", description = "Bad Request", content = [Content(schema = Schema(implementation = ErrorResponse::class))]),
        ]
    )
    fun addBreastFeeding(request: BreastFeedingDto.Request)

    @Operation(
        summary = "분유 기록 추가 API",
        operationId = "addBreastFeeding",
        description = """""",
        responses = [
            ApiResponse(responseCode = "200", description = "OK"),
            ApiResponse(responseCode = "400", description = "Bad Request", content = [Content(schema = Schema(implementation = ErrorResponse::class))]),
        ]
    )
    fun addBabyFormula(request: BabyFormulaDto.Request)

    @Operation(
        summary = "유축 기록 추가 API",
        operationId = "addBreastFeeding",
        description = """""",
        responses = [
            ApiResponse(responseCode = "200", description = "OK"),
            ApiResponse(responseCode = "400", description = "Bad Request", content = [Content(schema = Schema(implementation = ErrorResponse::class))]),
        ]
    )
    fun addBreastPumping(request: BreastPumpingDto.Request)

    @Operation(
        summary = "이유식 기록 추가 API",
        operationId = "addBreastFeeding",
        description = """""",
        responses = [
            ApiResponse(responseCode = "200", description = "OK"),
            ApiResponse(responseCode = "400", description = "Bad Request", content = [Content(schema = Schema(implementation = ErrorResponse::class))]),
        ]
    )
    fun addBabyFood(request: BabyFoodDto.Request)

    @Operation(
        summary = "기저귀 기록 추가 API",
        operationId = "addBreastFeeding",
        description = """""",
        responses = [
            ApiResponse(responseCode = "200", description = "OK"),
            ApiResponse(responseCode = "400", description = "Bad Request", content = [Content(schema = Schema(implementation = ErrorResponse::class))]),
        ]
    )
    fun addDiaper(request: DiaperDto.Request)

    @Operation(
        summary = "목욕 기록 추가 API",
        operationId = "addBreastFeeding",
        description = """""",
        responses = [
            ApiResponse(responseCode = "200", description = "OK"),
            ApiResponse(responseCode = "400", description = "Bad Request", content = [Content(schema = Schema(implementation = ErrorResponse::class))]),
        ]
    )
    fun addBath(request: BathDto.Request)

    @Operation(
        summary = "수면 기록 추가 API",
        operationId = "addBreastFeeding",
        description = """""",
        responses = [
            ApiResponse(responseCode = "200", description = "OK"),
            ApiResponse(responseCode = "400", description = "Bad Request", content = [Content(schema = Schema(implementation = ErrorResponse::class))]),
        ]
    )
    fun addSleep(request: SleepDto.Request)

    @Operation(
        summary = "건강 기록 추가 API",
        operationId = "addBreastFeeding",
        description = """""",
        responses = [
            ApiResponse(responseCode = "200", description = "OK"),
            ApiResponse(responseCode = "400", description = "Bad Request", content = [Content(schema = Schema(implementation = ErrorResponse::class))]),
        ]
    )
    fun addHealth(request: HealthDto.Request)

    @Operation(
        summary = "간식 기록 추가 API",
        operationId = "addBreastFeeding",
        description = """""",
        responses = [
            ApiResponse(responseCode = "200", description = "OK"),
            ApiResponse(responseCode = "400", description = "Bad Request", content = [Content(schema = Schema(implementation = ErrorResponse::class))]),
        ]
    )
    fun addSnack(request: SnackDto.Request)

    companion object {
        const val CARE_HISTORY = "/care-history"
    }
}
