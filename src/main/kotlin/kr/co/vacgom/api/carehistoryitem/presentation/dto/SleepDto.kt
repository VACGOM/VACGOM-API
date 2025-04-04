package kr.co.vacgom.api.carehistoryitem.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.vacgom.api.carehistoryitem.domain.Sleep
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import java.time.LocalDateTime
import java.util.*

class SleepDto {
    @Schema(name = "SleepDto.Request")
    data class Request(
        val babyId: UUID,
        val startDate: LocalDateTime,
        val endDate: LocalDateTime,
        val executionTime: LocalDateTime,
    )

    class Response {
        @Schema(name = "BabyFoodDto.Response.CustomDateStats")
        class CustomDateStats(
            careName: String,
            val averageAmount: String,
        ): AbstractStatDto(careName){
            companion object {
                fun of(type: CareHistoryItemType, dayCount: Int, items: List<Sleep>): CustomDateStats {
                    return CustomDateStats(
                        careName = type.typeName,
                        averageAmount = (items.sumOf { it.minutes } / dayCount).toString(),
                    )
                }
            }
        }

        @Schema(name = "SleepDto.Response.FixedDateStats")
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
                    beforeItems: List<Sleep>,
                    nowItems: List<Sleep>
                ): FixedDateStats {
                    val nowAmount = nowItems.sumOf { it.minutes } / dayCount
                    val beforeAmount = beforeItems.sumOf { it.minutes } / dayCount
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

        @Schema(name = "SleepDto.Response.DailyStats")
        class DailyStats(
            careName: String,
            val lastExecutionTime: LocalDateTime?,
            val hours: Int,
            val minutes: Int,
        ): AbstractStatDto(careName) {
            companion object {
                fun of(type: CareHistoryItemType, items: List<Sleep>): DailyStats {
                    val totalMinutes = items.sumOf { it.minutes }

                    return DailyStats(
                        careName = type.typeName,
                        lastExecutionTime = if (items.isNotEmpty()) { items.first().executionTime } else null,
                        hours = totalMinutes / 60,
                        minutes = totalMinutes % 60
                    )
                }
            }
        }

        @Schema(name = "SleepDto.Response.Detail")
        class Detail(
            careName: String,
            val startTime: LocalDateTime,
            val endTime: LocalDateTime,
            val executionTime: LocalDateTime,
        ): AbstractDetailDto(careName) {
            companion object {
                fun of(item: Sleep): Detail {
                    return Detail(
                        careName = item.itemType.typeName,
                        startTime = item.startTime,
                        endTime = item.endTime,
                        executionTime = item.executionTime
                    )
                }
            }
        }
    }
}
