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

    fun getBabyByIdAndUserIsAdmin(userId: UUID, babyId: UUID): Baby {
        return babyManagerRepository.findByBabyIdAndUserIdAndAdminIs(userId, babyId, true).baby
    }

    fun getBabiesByUserIsAdmin(userId: UUID): List<Baby> {
        return babyManagerRepository.findByUserIdAndAdminIs(userId, true).map { it.baby }
    }

    fun getBabiesByUserId(userId: UUID): List<Baby> {
        return babyManagerRepository.findByUserId(userId).map { it.baby }.sortedBy { it.birthday }
    }
}
