package kr.co.vacgom.api.carehistoryitem.repository

import kr.co.vacgom.api.carehistoryitem.domain.CareHistoryItem
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface CareHistoryItemJpaRepository : JpaRepository<CareHistoryItem, Long> {

    fun findByExecutionTimeBetween(startExecutionDate: LocalDateTime, endExecutionDate: LocalDateTime): List<CareHistoryItem>
}
