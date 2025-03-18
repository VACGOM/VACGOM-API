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
class CareHistoryItemQueryService(
    private val careHistoryItemRepository: CareHistoryItemRepository
) {
    fun getCareHistoryStatsByExecutionDate(babyId: UUID, executionDate: LocalDate): CareHistoryDto.Response {
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

    fun getCareHistoryStatsByFixedDate(
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

                DateType.MONTHLY -> {
                    val nowItems = careHistoryItemRepository.findByBabyIdAndExecutionDateBetween(babyId, startDate, endDate)
                    val beforeItems = careHistoryItemRepository.findByBabyIdAndExecutionDateBetween(
                        babyId = babyId,
                        startDate = startDate.minusMonths(1),
                        endDate = endDate.minusMonths(1)
                    )

                    Pair(beforeItems, nowItems)
                }

                else -> throw IllegalArgumentException("Unsupported date type $dateType")
        }

        return CareHistoryDto.Response.FixedDateStats.of(
            babyId = babyId,
            startDate = startDate,
            endDate = endDate,
            beforeCareHistory = beforeCareHistory,
            nowCareHistory = nowCareHistory
        )
    }

    fun getCareHistoryStatsByCustomDate(
        babyId: UUID,
        startDate: LocalDate,
        endDate: LocalDate
    ): CareHistoryDto.Response {
        val careHistory = careHistoryItemRepository.findByBabyIdAndExecutionDateBetween(babyId, startDate, endDate)

        return CareHistoryDto.Response.CustomDateStats.of(
            babyId = babyId,
            startDate = startDate,
            endDate = endDate,
            careHistory = careHistory
        )
    }
}
