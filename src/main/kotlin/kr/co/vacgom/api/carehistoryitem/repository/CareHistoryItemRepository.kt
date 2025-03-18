package kr.co.vacgom.api.carehistoryitem.repository

import kr.co.vacgom.api.baby.domain.Baby
import kr.co.vacgom.api.carehistoryitem.domain.CareHistory
import kr.co.vacgom.api.carehistoryitem.domain.CareHistoryItem
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import java.time.LocalDate

interface CareHistoryItemRepository {
    fun createCareHistoryItem(item: CareHistoryItem)
    fun findByBabyAndExecutionDateOrderByDesc(baby: Baby, executionDate: LocalDate): CareHistory
    fun findByBabyAndExecutionDateAndItemType(baby: Baby, executionDate: LocalDate, itemType: CareHistoryItemType): List<CareHistoryItem>
    fun findByBabyAndExecutionDateBetween(baby: Baby, startDate: LocalDate, endDate: LocalDate): CareHistory
}
