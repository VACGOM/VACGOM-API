package kr.co.vacgom.api.carehistoryitem.domain

import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import kr.co.vacgom.api.global.util.UuidCreator
import java.time.LocalDateTime
import java.util.*

class BreastPumping (
    val id: UUID = UuidCreator.create(),
    val amount: Int,
    itemType: CareHistoryItemType,
    executionDate: LocalDateTime,
): CareHistoryItem(executionDate, itemType) {
    companion object {
        fun create(amount: Int, executionDate: LocalDateTime, itemType: CareHistoryItemType): BreastPumping {
            return BreastPumping(amount = amount, executionDate = executionDate, itemType = itemType)
        }
    }
}
