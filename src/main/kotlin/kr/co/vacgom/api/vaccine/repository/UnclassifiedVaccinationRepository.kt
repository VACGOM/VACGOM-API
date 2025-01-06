package kr.co.vacgom.api.vaccine.repository

import kr.co.vacgom.api.vaccine.domain.UnclassifiedVaccination

interface UnclassifiedVaccinationRepository {

    fun save(unclassifiedVaccination: UnclassifiedVaccination): UnclassifiedVaccination
}
