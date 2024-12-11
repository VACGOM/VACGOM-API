package kr.co.vacgom.api.baby.application

import kr.co.vacgom.api.baby.repository.BabyRepository
import org.springframework.stereotype.Service
import kr.co.vacgom.api.baby.Baby
@Service
class BabyService(
    private val babyRepository: BabyRepository
) {
    fun saveAll(babies: Collection<Baby>): List<Baby> {
        return babyRepository.saveAll(babies)
    }
}
