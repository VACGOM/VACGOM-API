package kr.co.vacgom.api.vaccine.repository.vaccine

import kr.co.vacgom.api.global.exception.error.BusinessException
import kr.co.vacgom.api.vaccine.domain.Vaccine
import kr.co.vacgom.api.vaccine.exception.VaccinationError
import org.springframework.stereotype.Repository

@Repository
class VaccineRepositoryAdapter(
    private val vaccineJpaRepository: VaccineJpaRepository
) : VaccineRepository {

    override fun findByName(name: String): Vaccine {
        return vaccineJpaRepository.findByName(name) ?: throw BusinessException(VaccinationError.VACCINE_NOT_FOUND)
    }

    override fun findAll(): List<Vaccine> {
        return vaccineJpaRepository.findAll()
    }
}
