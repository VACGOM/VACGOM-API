package kr.co.vacgom.api.carehistoryitem.repository

import kr.co.vacgom.api.carehistoryitem.domain.CareHistory
import kr.co.vacgom.api.carehistoryitem.domain.CareHistoryItem
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

@Repository
class CareHistoryItemAdapter(
    private val careHistoryItemJpaRepository: CareHistoryItemJpaRepository,
): CareHistoryItemRepository {
    override fun saveHistoryItem(item: CareHistoryItem) {
        careHistoryItemJpaRepository.save(item)
    }

    override fun findByBabyIdAndExecutionDate(babyId: UUID, executionDate: LocalDate): CareHistory {
        val startExecutionDateTime = LocalDateTime.of(executionDate, LocalTime.MIN)
        val endExecutionDateTime = LocalDateTime.of(executionDate, LocalTime.MAX)

        val careHistoryItems = careHistoryItemJpaRepository.findByExecutionTimeBetween(
            startExecutionDateTime,
            endExecutionDateTime
        )

        return CareHistory(
            babyId,
            executionDate,
            careHistoryItems.groupBy { it.itemType }
        )
    }
}
