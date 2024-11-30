package kr.co.vacgom.api.carehistoryitem.domain

import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import kr.co.vacgom.api.global.util.UuidCreator
import java.time.LocalDateTime
import java.util.*

class Health (
    val id: UUID = UuidCreator.create(),
    val temperature: Double,
    val memo: String,
    itemType: CareHistoryItemType,
    executionDate: LocalDateTime,
): CareHistoryItem(executionDate, itemType) {
    companion object {
        fun create(temperature: Double,
                   memo: String,
                   executionDate: LocalDateTime,
                   itemType: CareHistoryItemType
        ): Health {
            return Health(
                temperature = temperature,
                memo = memo,
                executionDate = executionDate,
                itemType = itemType
            )
        }
    }
}
