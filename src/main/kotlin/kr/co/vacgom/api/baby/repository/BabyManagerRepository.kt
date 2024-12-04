package kr.co.vacgom.api.baby.repository

import kr.co.vacgom.api.babymanager.BabyManager

interface BabyManagerRepository {
    fun save(babyManager: BabyManager): BabyManager
    fun saveAll(managers: Collection<BabyManager>): List<BabyManager>
}
