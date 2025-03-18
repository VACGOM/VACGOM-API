package kr.co.vacgom.api.carehistoryitem.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.vacgom.api.carehistoryitem.domain.Bath
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import java.time.LocalDateTime
import java.util.*

class BathDto {
    @Schema(name = "BathDto.Request")
    data class Request(
        val babyId: UUID,
        val startDate: LocalDateTime,
        val endDate: LocalDateTime,
        val executionTime: LocalDateTime,
    )

    class Response {
        @Schema(name = "BathDto.Response.CustomDateStats")
        class CustomDateStats(
            careName: String,
            val averageAmount: String,
        ): AbstractStatDto(careName){
            companion object {
                fun of(type: CareHistoryItemType, dayCount: Int, items: List<Bath>): CustomDateStats {
                    return CustomDateStats(
                        careName = type.typeName,
                        averageAmount = (items.sumOf { it.minutes } / dayCount).toString(),
                    )
                }
            }
        }

        @Schema(name = "BathDto.Response.FixedDateStats")
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
                    beforeItems: List<Bath>,
                    nowItems: List<Bath>
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

        @Schema(name = "BathDto.Response.DailyStats")
        class DailyStats(
            careName: String,
            val hours: Int,
            val minutes: Int,
        ): AbstractStatDto(careName) {
            companion object {
                fun of(type: CareHistoryItemType, items: List<Bath>): DailyStats {
                    val totalMinutes = items.sumOf { it.minutes }

                    return DailyStats(
                        careName = type.typeName,
                        hours = totalMinutes / 60,
                        minutes = totalMinutes % 60,
                    )
                }
            }
        }

        @Schema(name = "BathDto.Response.Detail")
        class Detail(
            careName: String,
            val startTime: LocalDateTime,
            val endTime: LocalDateTime,
            val executionTime: LocalDateTime,
        ): AbstractDetailDto(careName) {
            companion object {
                fun of(item: Bath): Detail {
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
