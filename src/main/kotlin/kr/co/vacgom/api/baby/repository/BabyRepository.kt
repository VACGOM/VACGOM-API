package kr.co.vacgom.api.baby.repository

import kr.co.vacgom.api.baby.domain.Baby
import java.util.*

interface BabyRepository {
    fun save(newBaby: Baby): Baby
    fun saveAll(babies: List<Baby>): List<Baby>
    fun findBabiesById(ids: List<UUID>): List<Baby>
    fun findById(id: UUID): Baby
    fun findAll(): List<Baby>
    fun deleteBaby(baby: Baby)
}
