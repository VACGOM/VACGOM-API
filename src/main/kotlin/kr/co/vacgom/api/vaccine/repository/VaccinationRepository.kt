package kr.co.vacgom.api.vaccine.repository

import kr.co.vacgom.api.vaccine.domain.Vaccination

interface VaccinationRepository {

    fun save(vaccination: Vaccination): Vaccination
}
