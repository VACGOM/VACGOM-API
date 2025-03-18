package kr.co.vacgom.api.carehistoryitem.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.vacgom.api.carehistoryitem.domain.BreastFeeding
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import java.time.LocalDateTime
import java.util.*

class BreastFeedingDto {
    @Schema(name = "BreastFeedingDto.Request")
    data class Request(
        val babyId: UUID,
        val leftStartDate: LocalDateTime,
        val leftEndDate: LocalDateTime,
        val rightStartDate: LocalDateTime,
        val rightEndDate: LocalDateTime,
        val executionTime: LocalDateTime,
    )

    class Response {
        @Schema(name = "BabyFoodDto.Response.CustomDateStats")
        class CustomDateStats(
            careName: String,
            val averageAmount: String,
        ): AbstractStatDto(careName){
            companion object {
                fun of(type: CareHistoryItemType, dayCount: Int, items: List<BreastFeeding>): CustomDateStats {
                    return CustomDateStats(
                        careName = type.typeName,
                        averageAmount = (items.sumOf { it.leftMinutes + it.rightMinutes } / dayCount).toString(),
                    )
                }
            }
        }

        @Schema(name = "BreastFeedingDto.Response.FixedDateStats")
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
                    beforeItems: List<BreastFeeding>,
                    nowItems: List<BreastFeeding>
                ): FixedDateStats {
                    val nowAmount = nowItems.sumOf { it.leftMinutes + it.rightMinutes} / dayCount
                    val beforeAmount = beforeItems.sumOf { it.leftMinutes + it.rightMinutes } / dayCount
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

        @Schema(name = "BreastFeedingDto.Response.DailyStats")
        class DailyStats(
            careName: String,
            val lastExecutionTime: LocalDateTime?,
            val leftStat: BreastFeedingStat,
            val rightStat: BreastFeedingStat,
        ): AbstractStatDto(careName) {
            companion object {
                fun of(type: CareHistoryItemType, items: List<BreastFeeding>): DailyStats {
                    return DailyStats(
                        careName = type.typeName,
                        lastExecutionTime = if (items.isNotEmpty()) { items.first().executionTime } else null,
                        leftStat = BreastFeedingStat(
                            items.sumOf { it.leftMinutes },
                            items.count { it.leftMinutes  != 0 }
                        ),
                        rightStat = BreastFeedingStat(
                            items.sumOf { it.leftMinutes },
                            items.count { it.leftMinutes != 0 }
                        ),
                    )
                }
            }
        }

        @Schema(name = "BreastFeeding.Response.Detail")
        class Detail(
            careName: String,
            val leftMinutes: Int?,
            val rightMinutes: Int?,
            val executionTime: LocalDateTime,
        ): AbstractDetailDto(careName) {
            companion object {
                fun of(item: BreastFeeding): Detail {
                    return Detail(
                        careName = item.itemType.typeName,
                        leftMinutes = item.leftMinutes,
                        rightMinutes = item.rightMinutes,
                        executionTime = item.executionTime
                    )
                }
            }
        }
    }

    data class BreastFeedingStat (
        val minutes: Int,
        val count: Int
    )
}
