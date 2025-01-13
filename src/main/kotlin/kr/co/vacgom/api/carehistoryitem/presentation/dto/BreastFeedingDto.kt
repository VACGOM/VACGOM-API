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
        @Schema(name = "BreastFeedingDto.Response.DailyStat")
        class DailyStat(
            careName: String,
            val leftStat: BreastFeedingStat,
            val rightStat: BreastFeedingStat,
        ): AbstractDailyStatDto(careName) {
            companion object {
                fun of(type: CareHistoryItemType, items: List<BreastFeeding>): DailyStat {
                    return DailyStat(
                        careName = type.typeName,
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
        ): AbstractDailyDetailDto(careName) {
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
