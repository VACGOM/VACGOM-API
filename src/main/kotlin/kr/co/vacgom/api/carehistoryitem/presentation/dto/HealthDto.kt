package kr.co.vacgom.api.carehistoryitem.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.vacgom.api.carehistoryitem.domain.Health
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import java.time.LocalDateTime
import java.util.*

class HealthDto {
    @Schema(name = "HealthDto.Request")
    data class Request(
        val babyId: UUID,
        val temperature: Double,
        val memo: String,
        val executionTime: LocalDateTime,
    )

    class Response {
        @Schema(name = "BabyFoodDto.Response.CustomDateStats")
        class CustomDateStats(
            careName: String,
            val averageAmount: String,
        ): AbstractStatDto(careName){
            companion object {
                fun of(type: CareHistoryItemType, dayCount: Int, items: List<Health>): CustomDateStats {
                    return CustomDateStats(
                        careName = type.typeName,
                        averageAmount = (items.sumOf { it.temperature } / dayCount).toString(),
                    )
                }
            }
        }

        @Schema(name = "HealthDto.Response.FixedDateStats")
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
                    beforeItems: List<Health>,
                    nowItems: List<Health>
                ): FixedDateStats {
                    val nowAmount = nowItems.sumOf { it.temperature } / dayCount
                    val beforeAmount = beforeItems.sumOf { it.temperature } / dayCount
                    val changeAmount = nowAmount - beforeAmount

                    return FixedDateStats(
                        careName = type.typeName,
                        averageAmount = String.format("%.1f", nowAmount),
                        changeAmount = String.format("%.1f", changeAmount),
                        changeRate = if (beforeAmount != 0.0) String.format("%.1f", (changeAmount / beforeAmount * 100)) else "0"
                    )
                }
            }
        }

        @Schema(name = "HealthDto.Response.DailyStats")
        class DailyStats(
            careName: String,
            val temperature: Double,
        ): AbstractStatDto(careName) {
            companion object {
                fun of(type: CareHistoryItemType, items: List<Health>): DailyStats {
                    if (items.isEmpty()) {
                        return DailyStats(
                            careName = type.typeName,
                            temperature = 0.0
                        )
                    }

                    return DailyStats(
                        careName = type.typeName,
                        temperature = items.sumOf { it.temperature } / items.size
                    )
                }
            }
        }

        @Schema(name = "HealthDto.Response.Detail")
        class Detail(
            careName: String,
            val temperature: Double,
            val memo: String,
            val executionTime: LocalDateTime,
        ): AbstractDetailDto(careName) {
            companion object {
                fun of(item: Health): Detail {
                    return Detail(
                        careName = item.itemType.typeName,
                        temperature = item.temperature,
                        memo = item.memo,
                        executionTime = item.executionTime
                    )
                }
            }
        }
    }
}
