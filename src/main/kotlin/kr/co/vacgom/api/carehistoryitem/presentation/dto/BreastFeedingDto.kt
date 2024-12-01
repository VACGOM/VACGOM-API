package kr.co.vacgom.api.carehistoryitem.presentation.dto

import kr.co.vacgom.api.carehistoryitem.domain.BreastFeeding
import kr.co.vacgom.api.carehistoryitem.domain.enums.BreastDirection
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import java.time.LocalDateTime
import java.util.*

class BreastFeedingDto {
    data class Request(
        val babyId: UUID,
        val startDate: LocalDateTime,
        val endDate: LocalDateTime,
        val breastDirection: BreastDirection,
        val executionDate: LocalDateTime,
    )

    class Response {
        class DailyStat(
            careName: String,
            val leftStat: BreastFeedingStat,
            val rightStat: BreastFeedingStat,
        ): AbstractDailyStatDto(careName) {
            companion object {
                fun of(type: CareHistoryItemType, items: List<BreastFeeding>): DailyStat {
                    val feedingItems = items.groupBy { it.breastDirection }.map { item ->
                        item.key to BreastFeedingStat(
                            minutes = item.value.sumOf { it.minutes },
                            count = item.value.size,
                        )
                    }.toMap()

                    return DailyStat(
                        careName = type.typeName,
                        leftStat = feedingItems[BreastDirection.LEFT]
                            ?: BreastFeedingStat( minutes = 0, count = 0,),
                        rightStat = feedingItems[BreastDirection.RIGHT]
                            ?: BreastFeedingStat( minutes = 0, count = 0,)
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