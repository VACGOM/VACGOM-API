package kr.co.vacgom.api.vaccine.repository

import kr.co.vacgom.api.vaccine.domain.UnclassifiedVaccination
import org.springframework.stereotype.Repository

@Repository
class UnclassifiedVaccinationRepositoryAdapter(
    private val unclassifiedVaccinationJpaRepository: UnclassifiedVaccinationJpaRepository
) : UnclassifiedVaccinationRepository {

    override fun save(unclassifiedVaccination: UnclassifiedVaccination): UnclassifiedVaccination {
        return unclassifiedVaccinationJpaRepository.save(unclassifiedVaccination)
    }
}
