package kr.co.vacgom.api.baby.repository

import kr.co.vacgom.api.baby.Baby
import org.springframework.stereotype.Repository

@Repository
class BabyRepositoryAdapter(private val babyJpaRepository: BabyJpaRepository): BabyRepository {
    override fun saveAll(babies: Collection<Baby>): List<Baby> {
        return babyJpaRepository.saveAll(babies)
    }
}
