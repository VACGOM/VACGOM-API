package kr.co.vacgom.api.carehistoryitem.application

import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import kr.co.vacgom.api.carehistoryitem.presentation.dto.CareHistoryDto
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
        
        return CareHistoryDto.Response.DailyStat.of(careHistory)
    }

    fun getCareHistoryItemsByItemTypeAndExecutionDate(
        babyId: UUID,
        itemType: CareHistoryItemType,
        executionDate: LocalDate
    ): CareHistoryDto.Response {
        val careHistory = careHistoryItemRepository.findByBabyIdAndExecutionDate(babyId, executionDate)

        return CareHistoryDto.Response.CareHistoryItemDaily(
            babyId,
            executionDate,
            careHistory.careHistoryItems[itemType]
        )
    }
}
