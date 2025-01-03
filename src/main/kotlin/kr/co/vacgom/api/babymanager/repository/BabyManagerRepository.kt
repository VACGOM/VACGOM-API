package kr.co.vacgom.api.babymanager.repository

import kr.co.vacgom.api.babymanager.domain.BabyManager
import java.util.*

interface BabyManagerRepository {
    fun save(babyManager: BabyManager): BabyManager
    fun saveAll(managers: Collection<BabyManager>): List<BabyManager>
    fun findByUserIdAndAdminIs(userId: UUID, isAdmin: Boolean): List<BabyManager>
    fun findByBabyIdAndUserIdAndAdminIs(userId: UUID, babyId: UUID, isAdmin: Boolean): BabyManager
    fun findByUserId(userId: UUID): List<BabyManager>
    fun deleteByBabyIdAndUserIdAndAdminIs(userId: UUID, babyId: UUID, isAdmin: Boolean)
}
