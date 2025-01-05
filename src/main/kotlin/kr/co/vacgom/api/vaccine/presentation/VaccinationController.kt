package kr.co.vacgom.api.vaccine.presentation

import kr.co.vacgom.api.global.presentation.GlobalPath.BASE_V3
import kr.co.vacgom.api.vaccine.application.VaccinationService
import kr.co.vacgom.api.vaccine.presentation.VaccinationApi.Companion.VACCINATIONS
import kr.co.vacgom.api.vaccine.presentation.dto.VaccinationDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(BASE_V3 + VACCINATIONS)
class VaccinationController(
    private val vaccinationService: VaccinationService
) : VaccinationApi {

    @PostMapping
    override fun createVaccinations(@RequestBody request: VaccinationDto.Request.Create) {
        vaccinationService.createVaccinations(request)
    }
}
