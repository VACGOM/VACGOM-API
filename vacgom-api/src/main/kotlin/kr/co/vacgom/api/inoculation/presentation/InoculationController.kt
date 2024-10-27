package kr.co.vacgom.api.inoculation.presentation

import kr.co.vacgom.api.global.presentation.GlobalPath.BASE_URL
import kr.co.vacgom.api.inoculation.application.InoculationCreateService
import kr.co.vacgom.api.inoculation.application.InoculationReadService
import kr.co.vacgom.api.inoculation.application.dto.CreateInoculations
import kr.co.vacgom.api.inoculation.presentation.InoculationPath.INOCULATIONS
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(BASE_URL)
class InoculationController(
    private val inoculationCreateService: InoculationCreateService,
    private val inoculationReadService: InoculationReadService
) {

    @PostMapping(INOCULATIONS)
    fun createInoculations(
        @RequestBody request: CreateInoculations.ClientRequest
    ) {
        inoculationCreateService.createInoculations(request)
    }
}
