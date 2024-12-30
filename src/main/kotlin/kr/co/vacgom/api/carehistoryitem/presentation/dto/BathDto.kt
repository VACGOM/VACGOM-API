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
        @Schema(name = "BathDto.Response.DailyStat")
        class DailyStat(
            careName: String,
            val hours: Int,
            val minutes: Int,
        ): AbstractDailyStatDto(careName) {
            companion object {
                fun of(type: CareHistoryItemType, items: List<Bath>): DailyStat {
                    val totalMinutes = items.sumOf { it.minutes }

                    return DailyStat(
                        careName = type.typeName,
                        hours = totalMinutes / 60,
                        minutes = totalMinutes % 60,
                    )
                }
            }
        }
    }
}
