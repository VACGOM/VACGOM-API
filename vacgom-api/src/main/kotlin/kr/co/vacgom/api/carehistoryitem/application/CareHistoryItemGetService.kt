package kr.co.vacgom.api.carehistoryitem.application

import kr.co.vacgom.api.carehistoryitem.presentation.dto.CareHistoryDto
import kr.co.vacgom.api.carehistoryitem.repository.CareHistoryItemRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class CareHistoryItemGetService(
    private val careHistoryItemRepository: CareHistoryItemRepository
) {
    fun getCareHistoryByExecutionDate(babyId: UUID, executionDate: LocalDateTime): CareHistoryDto.Response.Daily {
        val careHistory = careHistoryItemRepository.findByBabyIdAndExecutionDate(babyId, executionDate)

        return CareHistoryDto.Response.Daily.of(careHistory)
    }
}
