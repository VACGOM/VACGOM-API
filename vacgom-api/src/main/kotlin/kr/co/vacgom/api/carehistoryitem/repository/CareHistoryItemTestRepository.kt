package kr.co.vacgom.api.carehistoryitem.repository

import kr.co.vacgom.api.carehistoryitem.domain.CareHistory
import kr.co.vacgom.api.carehistoryitem.domain.CareHistoryItem
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*

@Repository
class CareHistoryItemTestRepository(
    private val db: MutableList<CareHistoryItem> = mutableListOf(),
): CareHistoryItemRepository {

    override fun saveHistoryItem(item: CareHistoryItem) {
        db.add(item)
    }

    override fun findByBabyIdAndExecutionDate(babyId: UUID, executionDate: LocalDateTime): CareHistory {
        val careHistoryItemsByType = db.groupBy { it.itemType }
        return CareHistory(babyId, executionDate, careHistoryItemsByType)
    }
}
