package kr.co.vacgom.api.carehistoryitem.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.vacgom.api.carehistoryitem.domain.BabyFood
import kr.co.vacgom.api.carehistoryitem.domain.Health
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import kr.co.vacgom.api.carehistoryitem.domain.enums.ExcrementType
import kr.co.vacgom.api.carehistoryitem.presentation.dto.BabyFoodDto.Response.DailyDetail
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
        @Schema(name = "HealthDto.Response.DailyDetail")
        class DailyDetail(
            careName: String,
            val temperature: Double,
            val memo: String,
            val executionTime: LocalDateTime,
        ): AbstractDailyDetailDto(careName) {
            companion object {
                fun of(item: Health): DailyDetail {
                    return DailyDetail(
                        careName = item.itemType.typeName,
                        temperature = item.temperature,
                        memo = item.memo,
                        executionTime = item.executionTime,
                    )
                }
            }
        }

        @Schema(name = "HealthDto.Response.DailyStat")
        class DailyStat(
            careName: String,
            val temperature: Double,
        ): AbstractDailyStatDto(careName) {
            companion object {
                fun of(type: CareHistoryItemType, items: List<Health>): DailyStat {
                    if (items.isEmpty()) {
                        return DailyStat(
                            careName = type.typeName,
                            temperature = 0.0
                        )
                    }

                    return DailyStat(
                        careName = type.typeName,
                        temperature = items.sumOf { it.temperature } / items.size
                    )
                }
            }
        }
    }
}
