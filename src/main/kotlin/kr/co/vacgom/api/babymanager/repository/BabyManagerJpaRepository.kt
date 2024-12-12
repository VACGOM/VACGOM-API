package kr.co.vacgom.api.babymanager.repository

import kr.co.vacgom.api.babymanager.domain.BabyManager
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface BabyManagerJpaRepository: JpaRepository<BabyManager, UUID>
