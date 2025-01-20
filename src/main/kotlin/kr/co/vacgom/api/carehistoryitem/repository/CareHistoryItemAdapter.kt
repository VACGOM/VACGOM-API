package kr.co.vacgom.api.carehistoryitem.repository

import kr.co.vacgom.api.carehistoryitem.domain.CareHistory
import kr.co.vacgom.api.carehistoryitem.domain.CareHistoryItem
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
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

        val careHistoryItems = careHistoryItemJpaRepository.findByBabyIdAndExecutionTimeBetween(
            babyId,
            startExecutionDateTime,
            endExecutionDateTime
        )

        return CareHistory.create(
            babyId = babyId,
            startTime = startExecutionDateTime,
            endTime = endExecutionDateTime,
            careHistoryItems.groupBy { it.itemType }
        )
    }

    override fun findByBabyIdAndExecutionDateAndItemType(
        babyId: UUID,
        executionDate: LocalDate,
        itemType: CareHistoryItemType,
    ): List<CareHistoryItem> {
        val startExecutionDateTime = LocalDateTime.of(executionDate, LocalTime.MIN)
        val endExecutionDateTime = LocalDateTime.of(executionDate, LocalTime.MAX)

        return careHistoryItemJpaRepository.findByBabyIdAndItemTypeAndExecutionTimeBetween(
            babyId = babyId,
            itemType = itemType,
            startExecutionDate = startExecutionDateTime,
            endExecutionDate =  endExecutionDateTime
        )
    }

    override fun findByBabyIdAndExecutionDateBetween(
        babyId: UUID,
        startDate: LocalDate,
        endDate: LocalDate,
    ): CareHistory {
        val startExecutionDateTime = LocalDateTime.of(startDate, LocalTime.MIN)
        val endExecutionDateTime = LocalDateTime.of(endDate, LocalTime.MAX)
        val careHistoryItems = careHistoryItemJpaRepository.findByBabyIdAndExecutionTimeBetween(
            babyId = babyId,
            startExecutionDate = startExecutionDateTime,
            endExecutionDate = endExecutionDateTime
        )
        return CareHistory.create(
            babyId = babyId,
            startTime = startExecutionDateTime,
            endTime = endExecutionDateTime,
            careHistoryItems.groupBy { it.itemType }
        )
    }
}
