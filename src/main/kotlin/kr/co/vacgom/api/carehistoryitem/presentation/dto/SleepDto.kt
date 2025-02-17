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

        @Schema(name = "SleepDto.Response.Detail")
        class Detail(
            careName: String,
            val startTime: LocalDateTime,
            val endTime: LocalDateTime,
            val executionTime: LocalDateTime,
        ): AbstractDailyDetailDto(careName) {
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
