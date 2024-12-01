package kr.co.vacgom.api.carehistoryitem.domain

import kr.co.vacgom.api.carehistoryitem.domain.enums.BreastDirection
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import kr.co.vacgom.api.global.util.UuidCreator
import java.time.LocalDateTime
import java.util.*

class BreastFeeding (
    val id: UUID = UuidCreator.create(),
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val minutes: Int,
    val breastDirection: BreastDirection,
    itemType: CareHistoryItemType,
    executionDate: LocalDateTime,
): CareHistoryItem(executionDate, itemType) {
    companion object {
        fun create(
            startDate: LocalDateTime,
            endDate: LocalDateTime,
            minutes: Int,
            breastDirection: BreastDirection,
            executionDate: LocalDateTime,
            itemType: CareHistoryItemType,
        ): BreastFeeding {
            return BreastFeeding(
                startDate = startDate,
                endDate = endDate,
                minutes = minutes,
                breastDirection = breastDirection,
                executionDate = executionDate,
                itemType = itemType,
            )
        }
    }
}
