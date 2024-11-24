package kr.co.vacgom.api.carehistory.repository

import kr.co.vacgom.api.carehistory.domain.CareHistory
import org.springframework.stereotype.Repository

@Repository
class CareHistoryTestRepository(
    private val db: MutableList<CareHistory> = mutableListOf(),
): CareHistoryRepository {

    override fun save(careHistory: CareHistory) {
        db.add(careHistory)
    }
}
