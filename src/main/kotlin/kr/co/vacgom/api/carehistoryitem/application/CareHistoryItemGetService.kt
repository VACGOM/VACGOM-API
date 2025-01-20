package kr.co.vacgom.api.carehistoryitem.application

import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import kr.co.vacgom.api.carehistoryitem.presentation.dto.CareHistoryDto
import kr.co.vacgom.api.carehistoryitem.presentation.dto.enums.DateType
import kr.co.vacgom.api.carehistoryitem.repository.CareHistoryItemRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.util.*

@Service
@Transactional(readOnly = true)
class CareHistoryItemGetService(
    private val careHistoryItemRepository: CareHistoryItemRepository
) {
    fun getCareHistoryByExecutionDate(babyId: UUID, executionDate: LocalDate): CareHistoryDto.Response {
        val careHistory = careHistoryItemRepository.findByBabyIdAndExecutionDate(babyId, executionDate)
        
        return CareHistoryDto.Response.DailyStats.of(careHistory)
    }

    fun getCareHistoryItemsByItemTypeAndExecutionDate(
        babyId: UUID,
        itemType: CareHistoryItemType,
        executionDate: LocalDate
    ): CareHistoryDto.Response {
        val careHistoryItems = careHistoryItemRepository.findByBabyIdAndExecutionDateAndItemType(babyId, executionDate, itemType)

        return CareHistoryDto.Response.DailyDetail.of(
            babyId = babyId,
            executionDate = executionDate,
            itemType = itemType,
            items = careHistoryItems
        )
    }

    fun getCareHistoryStatsWithChangeMetaDataByInputDate(
        babyId: UUID,
        dateType: DateType,
        startDate: LocalDate,
        endDate: LocalDate
    ): CareHistoryDto.Response {
        val (beforeCareHistory, nowCareHistory) = when (dateType) {
                DateType.WEEKLY -> {
                    val nowItems = careHistoryItemRepository.findByBabyIdAndExecutionDateBetween(babyId, startDate, endDate)
                    val beforeItems = careHistoryItemRepository.findByBabyIdAndExecutionDateBetween(
                        babyId = babyId,
                        startDate = startDate.minusWeeks(1),
                        endDate = endDate.minusWeeks(1)
                    )

                    Pair(beforeItems, nowItems)
                }

                else -> {
                    val nowItems = careHistoryItemRepository.findByBabyIdAndExecutionDateBetween(babyId, startDate, endDate)
                    val beforeItems = careHistoryItemRepository.findByBabyIdAndExecutionDateBetween(
                        babyId = babyId,
                        startDate = startDate.minusMonths(1),
                        endDate = endDate.minusMonths(1)
                    )

                    Pair(beforeItems, nowItems)
                }
        }

        return CareHistoryDto.Response.FixedDateStats.of(
            babyId = babyId,
            startDate = startDate,
            endDate = endDate,
            beforeCareHistory = beforeCareHistory,
            nowCareHistory = nowCareHistory
        )
    }
}
