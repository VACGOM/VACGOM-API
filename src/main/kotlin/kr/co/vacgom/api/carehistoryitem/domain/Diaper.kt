package kr.co.vacgom.api.carehistoryitem.domain

import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import kr.co.vacgom.api.carehistoryitem.domain.enums.ExcrementType
import kr.co.vacgom.api.global.util.UuidCreator
import java.time.LocalDateTime
import java.util.*

class Diaper (
    val id: UUID = UuidCreator.create(),
    val excrementType: ExcrementType,
    itemType: CareHistoryItemType,
    executionDate: LocalDateTime,
): CareHistoryItem(executionDate, itemType) {
    companion object {
        fun create(excrementType: ExcrementType, executionDate: LocalDateTime, itemType: CareHistoryItemType): Diaper {
            return Diaper(excrementType = excrementType, executionDate = executionDate, itemType = itemType)
        }
    }
}
