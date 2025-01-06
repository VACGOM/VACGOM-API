package kr.co.vacgom.api.vaccine.repository.vaccine

import kr.co.vacgom.api.vaccine.domain.Vaccine

interface VaccineRepository {

    fun findByName(name: String): Vaccine
    fun findAll(): List<Vaccine>
}
