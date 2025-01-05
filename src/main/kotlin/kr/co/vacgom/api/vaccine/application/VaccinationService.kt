package kr.co.vacgom.api.vaccine.application

import kr.co.vacgom.api.baby.repository.BabyRepository
import kr.co.vacgom.api.global.exception.error.BusinessException
import kr.co.vacgom.api.vaccine.domain.UnclassifiedVaccination
import kr.co.vacgom.api.vaccine.domain.Vaccination
import kr.co.vacgom.api.vaccine.presentation.dto.VaccinationDto
import kr.co.vacgom.api.vaccine.repository.UnclassifiedVaccinationRepository
import kr.co.vacgom.api.vaccine.repository.VaccinationRepository
import kr.co.vacgom.api.vaccine.repository.vaccine.VaccineRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class VaccinationService(
    private val unclassifiedVaccinationRepository: UnclassifiedVaccinationRepository,
    private val vaccinationRepository: VaccinationRepository,
    private val vaccineRepository: VaccineRepository,
    private val babyRepository: BabyRepository
) {
    fun createVaccinations(request: VaccinationDto.Request.Create) {

        val baby = babyRepository.findById(request.babyId)

        request.vaccinationRequests.forEach {
            try {
                val vaccine = vaccineRepository.findByName(it.name)

                val classifiedVaccination = Vaccination(
                    doseRound = it.doseRound,
                    doseRoundDescription = it.doseRoundDescription,
                    vaccinatedAt = it.vaccinatedAt,
                    facility = it.facility,
                    manufacturer = it.manufacturer,
                    productName = it.productName,
                    lotNumber = it.lotNumber,
                    vaccine = vaccine,
                    baby = baby
                )

                vaccinationRepository.save(classifiedVaccination)
            } catch (businessException: BusinessException) {
                val unclassifiedVaccination = UnclassifiedVaccination(
                    name = it.name,
                    doseRound = it.doseRound,
                    doseRoundDescription = it.doseRoundDescription,
                    vaccinatedAt = it.vaccinatedAt,
                    facility = it.facility,
                    manufacturer = it.manufacturer,
                    productName = it.productName,
                    lotNumber = it.lotNumber,
                    baby = baby
                )

                unclassifiedVaccinationRepository.save(unclassifiedVaccination)
            }
        }
    }
}
