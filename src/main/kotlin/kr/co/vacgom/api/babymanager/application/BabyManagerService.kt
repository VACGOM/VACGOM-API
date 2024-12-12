package kr.co.vacgom.api.babymanager.application

import kr.co.vacgom.api.babymanager.repository.BabyManagerRepository
import kr.co.vacgom.api.babymanager.domain.BabyManager
import org.springframework.stereotype.Service

@Service
class BabyManagerService(
    private val babyManagerRepository: BabyManagerRepository,
) {
    fun saveAll(managers: Collection<BabyManager>): List<BabyManager> {
        return babyManagerRepository.saveAll(managers)
    }
}
