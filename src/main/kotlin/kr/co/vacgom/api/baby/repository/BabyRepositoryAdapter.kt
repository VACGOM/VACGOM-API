package kr.co.vacgom.api.baby.repository

import kr.co.vacgom.api.baby.domain.Baby
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class BabyRepositoryAdapter(private val babyJpaRepository: BabyJpaRepository): BabyRepository {
    override fun saveAll(babies: List<Baby>): List<Baby> {
        return babyJpaRepository.saveAll(babies)
    }

    override fun findBabiesById(ids: List<UUID>): List<Baby> {
        return babyJpaRepository.findAllById(ids)
    }
}
