package kr.co.vacgom.api.carehistoryitem.repository

import kr.co.vacgom.api.carehistoryitem.domain.CareHistory
import kr.co.vacgom.api.carehistoryitem.domain.CareHistoryItem
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.*

@Component
class CareHistoryItemTestRepository(
    private val db: MutableList<CareHistoryItem> = mutableListOf(),
) {
    fun saveHistoryItem(item: CareHistoryItem) {
        db.add(item)
    }

    fun findByBabyIdAndExecutionTime(babyId: UUID, executionDate: LocalDate): CareHistory {
        val careHistoryItemsByType = db.groupBy { it.itemType }
        return CareHistory(babyId, executionDate, careHistoryItemsByType)
    }
}
