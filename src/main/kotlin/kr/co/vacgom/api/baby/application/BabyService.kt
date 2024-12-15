package kr.co.vacgom.api.baby.application

import kr.co.vacgom.api.baby.domain.Baby
import kr.co.vacgom.api.baby.repository.BabyRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class BabyService(
    private val babyRepository: BabyRepository
) {
    fun saveAll(babies: List<Baby>): List<Baby> {
        return babyRepository.saveAll(babies)
    }

    fun getBabiesById(babyIds: List<UUID>): List<Baby> {
        return babyRepository.findBabiesById(babyIds)
    }
}
