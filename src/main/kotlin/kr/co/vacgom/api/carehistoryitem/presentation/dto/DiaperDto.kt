package kr.co.vacgom.api.carehistoryitem.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.vacgom.api.carehistoryitem.domain.Diaper
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import kr.co.vacgom.api.carehistoryitem.domain.enums.ExcrementType
import java.time.LocalDateTime
import java.util.*

class DiaperDto {
    @Schema(name = "DiaperDto.Request")
    data class Request(
        val babyId: UUID,
        val excrementType: ExcrementType,
        val executionTime: LocalDateTime,
    )

    class Response {
        @Schema(name = "BabyFoodDto.Response.CustomDateStats")
        class CustomDateStats(
            careName: String,
            val averageAmount: String,
        ): AbstractStatDto(careName){
            companion object {
                fun of(type: CareHistoryItemType, dayCount: Int, items: List<Diaper>): CustomDateStats {
                    return CustomDateStats(
                        careName = type.typeName,
                        averageAmount = (items.size / dayCount).toString(),
                    )
                }
            }
        }

        @Schema(name = "DiaperDto.Response.FixedDateStats")
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
                    beforeItems: List<Diaper>,
                    nowItems: List<Diaper>
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

        @Schema(name = "Diaper.Response.DailyStats")
        class DailyStats(
            careName: String,
            val count: Int,
        ): AbstractStatDto(careName) {
            companion object {
                fun of(type: CareHistoryItemType, items: List<Diaper>): DailyStats {
                    return DailyStats(type.typeName, count = items.size)
                }
            }
        }

        @Schema(name = "DiaperDto.Response.Detail")
        class Detail(
            careName: String,
            val excrementType: String,
            val executionTime: LocalDateTime,
        ): AbstractDetailDto(careName) {
            companion object {
                fun of(item: Diaper): Detail {
                    return Detail(
                        careName = item.itemType.typeName,
                        excrementType = item.excrementType.typeName,
                        executionTime = item.executionTime
                    )
                }
            }
        }
    }
}

