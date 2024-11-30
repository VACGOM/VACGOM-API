package kr.co.vacgom.api.carehistoryitem.domain

import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import kr.co.vacgom.api.global.util.UuidCreator
import java.time.LocalDateTime
import java.util.*

class BabyFormula (
    val id: UUID = UuidCreator.create(),
    val amount: Int,
    itemType: CareHistoryItemType,
    executionDate: LocalDateTime,
): CareHistoryItem(executionDate, itemType) {
    companion object {
        fun create(amount: Int, executionDate: LocalDateTime, itemType: CareHistoryItemType,): BabyFormula {
            return BabyFormula(amount = amount, executionDate = executionDate, itemType = itemType,)
        }
    }
}
