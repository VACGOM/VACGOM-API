package kr.co.vacgom.api.babymanager.repository

import kr.co.vacgom.api.babymanager.domain.BabyManager
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface BabyManagerJpaRepository: JpaRepository<BabyManager, UUID> {
    @Query("select bm from BabyManager bm where bm.user.id = :userId and bm.isAdmin = :isAdmin")
    fun findByUserIdAndAdminIs(@Param("userId") userId: UUID, @Param("isAdmin")isAdmin: Boolean): Set<BabyManager>
}
