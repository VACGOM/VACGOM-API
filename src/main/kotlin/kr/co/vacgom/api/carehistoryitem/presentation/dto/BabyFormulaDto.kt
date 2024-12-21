package kr.co.vacgom.api.carehistoryitem.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.vacgom.api.carehistoryitem.domain.BabyFormula
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import java.time.LocalDateTime
import java.util.*

class BabyFormulaDto {
    @Schema(name = "BabyFormulaDto.Request")
    data class Request(
        val babyId: UUID,
        val amount: Int,
        val executionTime: LocalDateTime,
    )

    class Response {
        @Schema(name = "BabyFormulaDto.Response.DailyStat")
        class DailyStat(
            careName: String,
            val amount: Int,
        ): AbstractDailyStatDto(careName) {
            companion object {
                fun of(type: CareHistoryItemType, items: List<BabyFormula>): DailyStat {
                    return DailyStat( careName = type.typeName, amount = items.sumOf { it.amount })
                }
            }
        }
    }
}
