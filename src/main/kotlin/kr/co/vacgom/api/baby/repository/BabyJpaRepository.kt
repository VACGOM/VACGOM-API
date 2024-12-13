package kr.co.vacgom.api.baby.repository

import kr.co.vacgom.api.baby.domain.Baby
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface BabyJpaRepository: JpaRepository<Baby, UUID>
