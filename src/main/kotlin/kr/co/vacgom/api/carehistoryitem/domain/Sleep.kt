package kr.co.vacgom.api.carehistoryitem.domain

import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import kr.co.vacgom.api.global.util.UuidCreator
import java.time.LocalDateTime
import java.util.*

class Sleep (
    val id: UUID = UuidCreator.create(),
    val minutes: Int,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    itemType: CareHistoryItemType,
    executionDate: LocalDateTime,
): CareHistoryItem(executionDate, itemType) {
    companion object {
        fun create(
            startDate: LocalDateTime,
            endDate: LocalDateTime,
            minutes: Int,
            executionDate: LocalDateTime,
            itemType: CareHistoryItemType,
        ): Sleep {
            return Sleep(
                startDate = startDate,
                endDate = endDate,
                minutes = minutes,
                executionDate = executionDate,
                itemType = itemType
            )
        }
    }
}
