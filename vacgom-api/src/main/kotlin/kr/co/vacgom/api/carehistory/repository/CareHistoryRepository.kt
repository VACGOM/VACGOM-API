package kr.co.vacgom.api.carehistory.repository

import kr.co.vacgom.api.carehistory.domain.CareHistory
import java.time.LocalDate

interface CareHistoryRepository {
    fun save(careHistory: CareHistory)
    fun findByRecordDate(recordDate: LocalDate): CareHistory?
}
