package kr.co.vacgom.api.baby.repository

import kr.co.vacgom.api.user.domain.Baby
import org.springframework.stereotype.Repository

@Repository
class BabyTestRepository(
    private val db: MutableList<Baby> = mutableListOf(),
): BabyRepository {
    override fun saveAll(babies: Collection<Baby>) {
        db.addAll(babies)
    }
}
