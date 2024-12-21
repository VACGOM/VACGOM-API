package kr.co.vacgom.api.baby.application

import kr.co.vacgom.api.baby.domain.Baby
import kr.co.vacgom.api.baby.exceptioin.BabyError
import kr.co.vacgom.api.baby.repository.BabyRepository
import kr.co.vacgom.api.global.exception.error.BusinessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class BabyService(
    private val babyRepository: BabyRepository
) {
    fun saveAll(babies: List<Baby>): List<Baby> {
        return babyRepository.saveAll(babies)
    }

    fun getBabiesById(babyIds: List<UUID>): List<Baby> {
        return babyRepository.findBabiesById(babyIds)
    }

    fun getBabyById(id: UUID): Baby {
        return babyRepository.findById(id) ?: throw BusinessException(BabyError.BABY_NOT_FOUND)
    }
}
