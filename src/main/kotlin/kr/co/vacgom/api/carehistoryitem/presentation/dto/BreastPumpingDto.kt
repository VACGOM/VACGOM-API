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

    class Response {
        @Schema(name = "BabyFoodDto.Response.CustomDateStats")
        class CustomDateStats(
            careName: String,
            val averageAmount: String,
        ): AbstractStatDto(careName){
            companion object {
                fun of(type: CareHistoryItemType, dayCount: Int, items: List<BreastPumping>): CustomDateStats {
                    return CustomDateStats(
                        careName = type.typeName,
                        averageAmount = (items.sumOf { it.amount } / dayCount).toString(),
                    )
                }
            }
        }

        @Schema(name = "BreastPumpingDto.Response.FixedDateStats")
        class FixedDateStats (
            careName: String,
            val averageAmount: String,
            val changeAmount: String,
            val changeRate: String,
        ): AbstractStatDto(careName){
            companion object {
                fun of(
                    type: CareHistoryItemType,
                    dayCount: Int,
                    beforeItems: List<BreastPumping>,
                    nowItems: List<BreastPumping>
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

        @Schema(name = "BreastPumpingDto.Response.DailyStats")
        class DailyStats(
            careName: String,
            val amount: Int,
        ): AbstractStatDto(careName) {
            companion object {
                fun of(type: CareHistoryItemType, items: List<BreastPumping>): DailyStats {
                    return DailyStats(careName = type.typeName, amount = items.sumOf { it.amount })
                }
            }
        }

        @Schema(name = "BreastPumping.Response.Detail")
        class Detail(
            careName: String,
            val amount: Int,
            val executionTime: LocalDateTime,
        ): AbstractDetailDto(careName) {
            companion object {
                fun of(item: BreastPumping): Detail {
                    return Detail(
                        careName = item.itemType.typeName,
                        amount = item.amount,
                        executionTime = item.executionTime
                    )
                }
            }
        }
    }
}
