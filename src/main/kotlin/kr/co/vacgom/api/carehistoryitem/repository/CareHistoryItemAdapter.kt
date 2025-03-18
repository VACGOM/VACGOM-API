package kr.co.vacgom.api.carehistoryitem.repository

import kr.co.vacgom.api.baby.domain.Baby
import kr.co.vacgom.api.carehistoryitem.domain.CareHistory
import kr.co.vacgom.api.carehistoryitem.domain.CareHistoryItem
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Repository
class CareHistoryItemAdapter(
    private val careHistoryItemJpaRepository: CareHistoryItemJpaRepository,
): CareHistoryItemRepository {
    override fun saveHistoryItem(item: CareHistoryItem) {
        careHistoryItemJpaRepository.save(item)
    }

    override fun findByBabyAndExecutionDate(baby: Baby, executionDate: LocalDate): CareHistory {
        val startExecutionDateTime = LocalDateTime.of(executionDate, LocalTime.MIN)
        val endExecutionDateTime = LocalDateTime.of(executionDate, LocalTime.MAX)

        val careHistoryItems = careHistoryItemJpaRepository.findByBabyAndExecutionTimeBetween(
            baby,
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

    override fun findByBabyAndExecutionDateAndItemType(
        baby: Baby,
        executionDate: LocalDate,
        itemType: CareHistoryItemType,
    ): List<CareHistoryItem> {
        val startExecutionDateTime = LocalDateTime.of(executionDate, LocalTime.MIN)
        val endExecutionDateTime = LocalDateTime.of(executionDate, LocalTime.MAX)

        return careHistoryItemJpaRepository.findByBabyAndItemTypeAndExecutionTimeBetween(
            baby = baby,
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
