package kr.co.vacgom.api.babymanager.repository

import kr.co.vacgom.api.babymanager.domain.BabyManager
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class BabyManagerRepositoryAdapter(
    private val babyManagerJpaRepository: BabyManagerJpaRepository
): BabyManagerRepository {
    override fun save(babyManager: BabyManager): BabyManager {
        return babyManagerJpaRepository.save(babyManager)
    }

    override fun saveAll(managers: Collection<BabyManager>): List<BabyManager> {
        return babyManagerJpaRepository.saveAll(managers)
    }

    override fun findByUserIdAndAdminIs(userId: UUID, isAdmin: Boolean): List<BabyManager> {
        return babyManagerJpaRepository.findByUserIdAndAdminIs(userId, isAdmin)
    }

    override fun findByBabyIdAndUserIdAndAdminIs(userId: UUID, babyId: UUID, isAdmin: Boolean): BabyManager {
        return babyManagerJpaRepository.findByBabyIdAndUserIdAndAdminIs(userId, babyId, isAdmin)
    }
}
