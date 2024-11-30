package kr.co.vacgom.api.carehistoryitem.repository

import kr.co.vacgom.api.carehistoryitem.domain.CareHistory
import kr.co.vacgom.api.carehistoryitem.domain.CareHistoryItem
import java.time.LocalDateTime
import java.util.*

interface CareHistoryItemRepository {
    fun saveHistoryItem(item: CareHistoryItem)
    fun findByBabyIdAndExecutionDate(
        babyId: UUID,
        executionDate: LocalDateTime
    ): CareHistory
}
