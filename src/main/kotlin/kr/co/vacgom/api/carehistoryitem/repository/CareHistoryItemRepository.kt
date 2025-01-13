package kr.co.vacgom.api.carehistoryitem.repository

import kr.co.vacgom.api.carehistoryitem.domain.CareHistory
import kr.co.vacgom.api.carehistoryitem.domain.CareHistoryItem
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import java.time.LocalDate
import java.util.*

interface CareHistoryItemRepository {
    fun saveHistoryItem(item: CareHistoryItem)
    fun findByBabyIdAndExecutionDate(babyId: UUID, executionDate: LocalDate): CareHistory
    fun findByBabyIdAndExecutionDateAndItemType(babyId: UUID, executionDate: LocalDate, itemType: CareHistoryItemType): List<CareHistoryItem>
}
