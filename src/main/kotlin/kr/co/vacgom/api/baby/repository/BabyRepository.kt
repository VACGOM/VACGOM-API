package kr.co.vacgom.api.baby.repository

import kr.co.vacgom.api.baby.domain.Baby
import java.util.*

interface BabyRepository {
    fun saveAll(babies: List<Baby>): List<Baby>
    fun findBabiesById(ids: List<UUID>): List<Baby>
}
