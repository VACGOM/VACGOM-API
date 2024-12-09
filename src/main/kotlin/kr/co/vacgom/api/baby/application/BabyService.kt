package kr.co.vacgom.api.baby.application

import kr.co.vacgom.api.baby.domain.Baby
import kr.co.vacgom.api.baby.repository.BabyRepository
import org.springframework.stereotype.Service

@Service
class BabyService(
    private val babyRepository: BabyRepository
) {
    fun saveAll(babies: Collection<Baby>): List<Baby> {
        return babyRepository.saveAll(babies)
    }
}
