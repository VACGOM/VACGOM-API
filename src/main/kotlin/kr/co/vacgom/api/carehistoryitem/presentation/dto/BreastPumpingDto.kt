package kr.co.vacgom.api.carehistoryitem.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.vacgom.api.carehistoryitem.domain.BreastPumping
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import java.time.LocalDateTime
import java.util.*

class BreastPumpingDto {
    @Schema(name = "BreastPumpingDto.Request")
    data class Request(
        val babyId: UUID,
        val amount: Int,
        val executionTime: LocalDateTime,
    )

    data class Response(
        val executionTime: LocalDateTime,
        val amount: Int,
    ) {
        @Schema(name = "BreastPumpingDto.Response.DailyStat")
        class DailyStat(
            careName: String,
            val amount: Int,
        ): AbstractDailyStatDto(careName) {
            companion object {
                fun of(type: CareHistoryItemType, items: List<BreastPumping>): DailyStat {
                    return DailyStat(careName = type.typeName, amount = items.sumOf { it.amount })
                }
            }
        }
    }
}
