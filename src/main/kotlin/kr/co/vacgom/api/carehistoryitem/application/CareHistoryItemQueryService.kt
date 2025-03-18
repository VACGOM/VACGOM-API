package kr.co.vacgom.api.carehistoryitem.application

import kr.co.vacgom.api.baby.application.BabyQueryService
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
    private val careHistoryItemRepository: CareHistoryItemRepository,
    private val babyQueryService: BabyQueryService
) {
    fun getCareHistoryStatsByExecutionDate(babyId: UUID, executionDate: LocalDate): CareHistoryDto.Response {
        val findBaby = babyQueryService.getBabyById(babyId)
        val careHistory = careHistoryItemRepository.findByBabyAndExecutionDateOrderByDesc(findBaby, executionDate)

        return CareHistoryDto.Response.DailyStats.of(careHistory)
    }

    fun getCareHistoryItemsByItemTypeAndExecutionDate(
        babyId: UUID,
        itemType: CareHistoryItemType,
        executionDate: LocalDate
    ): CareHistoryDto.Response {
        val findBaby = babyQueryService.getBabyById(babyId)
        val careHistoryItems = careHistoryItemRepository.findByBabyAndExecutionDateAndItemType(findBaby, executionDate, itemType)

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
                    val findBaby = babyQueryService.getBabyById(babyId)
                    val nowItems = careHistoryItemRepository.findByBabyAndExecutionDateBetween(findBaby, startDate, endDate)
                    val beforeItems = careHistoryItemRepository.findByBabyAndExecutionDateBetween(
                        baby = findBaby,
                        startDate = startDate.minusWeeks(1),
                        endDate = endDate.minusWeeks(1)
                    )

                    Pair(beforeItems, nowItems)
                }

                DateType.MONTHLY -> {
                    val findBaby = babyQueryService.getBabyById(babyId)
                    val nowItems = careHistoryItemRepository.findByBabyAndExecutionDateBetween(findBaby, startDate, endDate)
                    val beforeItems = careHistoryItemRepository.findByBabyAndExecutionDateBetween(
                        baby = findBaby,
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
        val findBaby = babyQueryService.getBabyById(babyId)
        val careHistory = careHistoryItemRepository.findByBabyAndExecutionDateBetween(findBaby, startDate, endDate)

        return CareHistoryDto.Response.CustomDateStats.of(
            babyId = babyId,
            startDate = startDate,
            endDate = endDate,
            careHistory = careHistory
        )
    }
}
