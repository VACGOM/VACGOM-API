package kr.co.vacgom.api.babymanager.repository

import kr.co.vacgom.api.babymanager.domain.BabyManager
import org.springframework.data.jpa.repository.JpaRepository

interface BabyManagerJpaRepository: JpaRepository<BabyManager, Long>
