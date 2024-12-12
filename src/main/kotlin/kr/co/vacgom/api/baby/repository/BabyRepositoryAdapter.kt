package kr.co.vacgom.api.baby.repository

import kr.co.vacgom.api.baby.domain.Baby
import org.springframework.stereotype.Repository

@Repository
class BabyRepositoryAdapter(private val babyJpaRepository: BabyJpaRepository): BabyRepository {
    override fun saveAll(babies: Collection<Baby>): List<Baby> {
        return babyJpaRepository.saveAll(babies)
    }
}
