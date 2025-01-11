package kr.co.vacgom.api.carehistoryitem.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.vacgom.api.carehistoryitem.domain.BabyFood
import kr.co.vacgom.api.carehistoryitem.domain.Sleep
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import kr.co.vacgom.api.carehistoryitem.presentation.dto.BabyFoodDto.Response.DailyDetail
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
        @Schema(name = "SleepDto.Response.DailyDetail")
        class DailyDetail(
            careName: String,
            val startTime: LocalDateTime,
            val endTime: LocalDateTime,
            val executionTime: LocalDateTime,
        ): AbstractDailyDetailDto(careName) {
            companion object {
                fun of(item: Sleep): DailyDetail {
                    return DailyDetail(
                        careName = item.itemType.typeName,
                        startTime = item.startTime,
                        endTime = item.endTime,
                        executionTime = item.executionTime
                    )
                }
            }
        }

        @Schema(name = "SleepDto.Response.DailyStat")
        class DailyStat(
            careName: String,
            val hours: Int,
            val minutes: Int,
        ): AbstractDailyStatDto(careName) {
            companion object {
                fun of(type: CareHistoryItemType, items: List<Sleep>): DailyStat {
                    val totalMinutes = items.sumOf { it.minutes }

                    return DailyStat(
                        careName = type.typeName,
                        hours = totalMinutes / 60,
                        minutes = totalMinutes % 60
                    )
                }
            }
        }
    }
}
