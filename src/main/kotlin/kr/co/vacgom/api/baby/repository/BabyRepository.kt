package kr.co.vacgom.api.baby.repository

import kr.co.vacgom.api.baby.domain.Baby

interface BabyRepository {
    fun saveAll(babies: Collection<Baby>): List<Baby>
}
