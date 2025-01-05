package kr.co.vacgom.api.vaccine.presentation

import kr.co.vacgom.api.global.presentation.GlobalPath.BASE_V3
import kr.co.vacgom.api.vaccine.presentation.VaccinationApi.Companion.VACCINATIONS
import kr.co.vacgom.api.vaccine.presentation.dto.VaccinationDto
import kr.co.vacgom.api.vaccine.repository.VaccinationRepository
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(BASE_V3 + VACCINATIONS)
class VaccinationController(
    private val vaccinationRepository: VaccinationRepository
) : VaccinationApi {

    override fun createVaccinations(request: List<VaccinationDto.Request.Create>) {
        
    }
}
