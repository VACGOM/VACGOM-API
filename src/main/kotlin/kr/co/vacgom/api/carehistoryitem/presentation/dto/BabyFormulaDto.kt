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
        @Schema(name = "BabyFormulaDto.Response.CustomDateStats")
        class CustomDateStats(
            careName: String,
            val averageAmount: String,
        ): AbstractStatDto(careName){
            companion object {
                fun of(type: CareHistoryItemType, dayCount: Int, items: List<BabyFormula>): CustomDateStats {
                    return CustomDateStats(
                        careName = type.typeName,
                        averageAmount = (items.sumOf { it.amount } / dayCount).toString(),
                    )
                }
            }
        }

        @Schema(name = "BabyFormulaDto.Response.FixedDateStats")
        class FixedDateStats(
            careName: String,
            val averageAmount: String,
            val changeAmount: String,
            val changeRate: String,
        ): AbstractStatDto(careName){
            companion object {
                fun of(
                    type: CareHistoryItemType,
                    dayCount: Int,
                    beforeItems: List<BabyFormula>,
                    nowItems: List<BabyFormula>
                ): FixedDateStats {
                    val nowAmount = nowItems.sumOf { it.amount } / dayCount
                    val beforeAmount = beforeItems.sumOf { it.amount } / dayCount
                    val changeAmount = nowAmount - beforeAmount

                    return FixedDateStats(
                        careName = type.typeName,
                        averageAmount = nowAmount.toString(),
                        changeAmount = changeAmount.toString(),
                        changeRate = if (beforeAmount != 0) (changeAmount / beforeAmount * 100).toString() else "0"
                    )
                }
            }
        }

        @Schema(name = "BabyFormulaDto.Response.DailyStats")
        class DailyStats(
            careName: String,
            val lastExecutionTime: LocalDateTime?,
            val amount: Int,
        ): AbstractStatDto(careName) {
            companion object {
                fun of(type: CareHistoryItemType, items: List<BabyFormula>): DailyStats {
                    return DailyStats(
                        careName = type.typeName,
                        lastExecutionTime = if (items.isNotEmpty()) { items.first().executionTime } else null,
                        amount = items.sumOf { it.amount }
                    )
                }
            }
        }

        @Schema(name = "BabyFormulaDto.Response.Detail")
        class Detail(
            careName: String,
            val amount: Int,
            val executionTime: LocalDateTime,
        ): AbstractDetailDto(careName) {
            companion object {
                fun of(item: BabyFormula): Detail {
                    return Detail(careName = item.itemType.typeName, amount = item.amount, executionTime = item.executionTime)
                }
            }
        }
    }
}
