package kr.co.vacgom.api.carehistoryitem.domain

import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import kr.co.vacgom.api.global.util.UuidCreator
import java.time.LocalDateTime
import java.util.*

class Snack (
    val id: UUID = UuidCreator.create(),
    val memo: String,
    itemType: CareHistoryItemType,
    executionDate: LocalDateTime,
): CareHistoryItem(executionDate, itemType) {
    companion object {
        fun create(memo: String, executionDate: LocalDateTime, itemType: CareHistoryItemType): Snack {
            return Snack(memo = memo, executionDate = executionDate, itemType = itemType)
        }
    }
}
