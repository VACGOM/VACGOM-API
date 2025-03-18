package kr.co.vacgom.api.carehistoryitem.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.vacgom.api.carehistoryitem.domain.Snack
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import java.time.LocalDateTime
import java.util.*

class SnackDto {
    @Schema(name = "SnackDto.Request")
    data class Request(
        val babyId: UUID,
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
                fun of(type: CareHistoryItemType, dayCount: Int, items: List<Snack>): CustomDateStats {
                    return CustomDateStats(
                        careName = type.typeName,
                        averageAmount = (items.size / dayCount).toString(),
                    )
                }
            }
        }

        @Schema(name = "SnackDto.Response.FixedDateStats")
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
                    beforeItems: List<Snack>,
                    nowItems: List<Snack>
                ): FixedDateStats {
                    val nowAmount = nowItems.size / dayCount
                    val beforeAmount = beforeItems.size / dayCount
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

        @Schema(name = "SnackDto.Response.DailyStats")
        class DailyStats(
            careName: String,
            val lastExecutionTime: LocalDateTime?,
            val count: Int,
        ): AbstractStatDto(careName) {
            companion object {
                fun of(type: CareHistoryItemType, items: List<Snack>): DailyStats {
                    return DailyStats(
                        careName = type.typeName,
                        lastExecutionTime = if (items.isNotEmpty()) { items.first().executionTime } else null,
                        count = items.size
                    )
                }
            }
        }

        @Schema(name = "SnackDto.Response.Detail")
        class Detail(
            careName: String,
            val memo: String?,
            val executionTime: LocalDateTime,
        ): AbstractDetailDto(careName) {
            companion object {
                fun of(item: Snack): Detail {
                    return Detail(
                        careName = item.itemType.typeName,
                        memo = item.memo,
                        executionTime = item.executionTime
                    )
                }
            }
        }
    }
}
