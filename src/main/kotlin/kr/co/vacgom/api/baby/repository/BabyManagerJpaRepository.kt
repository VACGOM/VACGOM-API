package kr.co.vacgom.api.baby.repository

import kr.co.vacgom.api.babymanager.BabyManager
import org.springframework.data.jpa.repository.JpaRepository

interface BabyManagerJpaRepository: JpaRepository<BabyManager, Long>
