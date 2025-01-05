package kr.co.vacgom.api.vaccine.repository

import kr.co.vacgom.api.vaccine.domain.Vaccination
import org.springframework.stereotype.Repository

@Repository
class VaccinationRepositoryAdapter(
    private val vaccinationJpaRepository: VaccinationJpaRepository
) : VaccinationRepository {

    override fun save(vaccination: Vaccination): Vaccination {
        return vaccinationJpaRepository.save(vaccination)
    }
}
