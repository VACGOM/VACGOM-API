package kr.co.vacgom.api.carehistoryitem.repository

import kr.co.vacgom.api.carehistoryitem.domain.CareHistoryItem
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime
import java.util.*

interface CareHistoryItemJpaRepository : JpaRepository<CareHistoryItem, Long> {
    fun findByBabyIdAndExecutionTimeBetween(babyId: UUID, startExecutionDate: LocalDateTime, endExecutionDate: LocalDateTime): List<CareHistoryItem>
}
