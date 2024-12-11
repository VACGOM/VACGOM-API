package kr.co.vacgom.api.baby.repository

import kr.co.vacgom.api.baby.Baby
import org.springframework.data.jpa.repository.JpaRepository

interface BabyJpaRepository: JpaRepository<Baby, Long>
