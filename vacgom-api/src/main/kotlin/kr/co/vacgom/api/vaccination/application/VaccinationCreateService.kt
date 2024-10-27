package kr.co.vacgom.api.vaccination.application

import kr.co.vacgom.api.vaccination.application.dto.CreateVaccinations
import kr.co.vacgom.persistence.vaccination.infrastructure.VaccinationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class VaccinationCreateService(
    private val vaccinationRepository: VaccinationRepository
) {
    fun createVaccinations(request: CreateVaccinations.ClientRequest) {
        
    }
}
