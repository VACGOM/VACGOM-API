package kr.co.vacgom.api.carehistoryitem.domain

import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import java.time.LocalDate
import java.util.*

data class CareHistory (
    val babyId: UUID,
    val executionDate: LocalDate,
    val careHistoryItems: Map<CareHistoryItemType, List<CareHistoryItem>>,
) {
    companion object {
        fun create(
            babyId: UUID,
            executionDate: LocalDate,
            careHistoryItems: Map<CareHistoryItemType, List<CareHistoryItem>>,
        ): CareHistory {
            return CareHistory(
                babyId = babyId,
                executionDate = executionDate,
                careHistoryItems = careHistoryItems,
            )
        }
    }
}
