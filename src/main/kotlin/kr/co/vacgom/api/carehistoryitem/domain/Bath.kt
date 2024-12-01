package kr.co.vacgom.api.carehistoryitem.domain

import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import kr.co.vacgom.api.global.util.UuidCreator
import java.time.LocalDateTime
import java.util.*

class Bath (
    val id: UUID = UuidCreator.create(),
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val minutes: Int,
    itemType: CareHistoryItemType,
    executionDate: LocalDateTime,
): CareHistoryItem(executionDate, itemType) {
    companion object {
        fun create(startDate: LocalDateTime,
                   endDate: LocalDateTime,
                   executionDate: LocalDateTime,
                   itemType: CareHistoryItemType,
                   minutes: Int,
        ): Bath {
            return Bath(
                startDate = startDate,
                endDate = endDate,
                executionDate = executionDate,
                itemType = itemType,
                minutes = minutes,
            )
        }
    }
}
