package kr.co.vacgom.api.carehistoryitem.domain

import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import java.time.LocalDateTime
import java.util.*

data class CareHistory (
    val babyId: UUID,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val careHistoryItems: Map<CareHistoryItemType, List<CareHistoryItem>>,
) {
    companion object {
        fun create(
            babyId: UUID,
            startTime: LocalDateTime,
            endTime: LocalDateTime,
            careHistoryItems: Map<CareHistoryItemType, List<CareHistoryItem>>,
        ): CareHistory {
            return CareHistory(
                babyId = babyId,
                startTime = startTime,
                endTime = endTime,
                careHistoryItems = careHistoryItems,
            )
        }
    }
}
