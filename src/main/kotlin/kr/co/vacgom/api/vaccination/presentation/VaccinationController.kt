package kr.co.vacgom.api.vaccination.presentation

import kr.co.vacgom.api.global.presentation.GlobalPath.BASE_V3
import kr.co.vacgom.api.vaccination.application.dto.CreateVaccinations
import kr.co.vacgom.api.vaccination.presentation.VaccinationPath.VACCINATIONS
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(BASE_V3 + VACCINATIONS)
class VaccinationController {

    @PostMapping
    fun createVaccinations(
        @RequestBody request: CreateVaccinations.ClientRequest
    ) {
    }
}
