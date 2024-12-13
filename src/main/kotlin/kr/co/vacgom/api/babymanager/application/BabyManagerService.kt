package kr.co.vacgom.api.babymanager.application

import kr.co.vacgom.api.baby.domain.Baby
import kr.co.vacgom.api.babymanager.domain.BabyManager
import kr.co.vacgom.api.babymanager.repository.BabyManagerRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class BabyManagerService(
    private val babyManagerRepository: BabyManagerRepository,
) {
    fun saveAll(managers: Collection<BabyManager>): List<BabyManager> {
        return babyManagerRepository.saveAll(managers)
    }

    fun getBabiesByUserIsAdmin(userId: UUID): Set<Baby> {
        return babyManagerRepository.findByUserIdAndIsAdmin(userId).map { it.baby }.toSet()
    }
}
