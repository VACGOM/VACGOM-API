package kr.co.vacgom.api.babymanager.repository

import kr.co.vacgom.api.babymanager.domain.BabyManager


interface BabyManagerRepository {
    fun save(babyManager: BabyManager): BabyManager
    fun saveAll(managers: Collection<BabyManager>): List<BabyManager>
}
