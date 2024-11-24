package kr.co.vacgom.api.carehistory.presentation

import kr.co.vacgom.api.carehistory.application.CareHistoryEditService
import kr.co.vacgom.api.carehistory.presentation.CareHistoryPath.CARE_HISTORY
import kr.co.vacgom.api.carehistory.presentation.dto.*
import kr.co.vacgom.api.global.presentation.GlobalPath.BASE_V3
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(BASE_V3 + CARE_HISTORY)
class CareHistoryEditController(
    private val careHistoryEditService: CareHistoryEditService
) {
    @PostMapping("/breast-feeding")
    fun addBreastFeeding(request: BreastFeedingDto.Request) {

    }

    @PostMapping("/baby-formula")
    fun addBabyFormula(request: BabyFormulaDto.Request) {

    }

    @PostMapping("/breast-pumping")
    fun addBreastPumping(request: BreastFeedingDto.Request) {

    }

    @PostMapping("/baby-food")
    fun addBabyFood(request: BabyFoodDto.Request) {

    }

    @PostMapping("/diaper")
    fun addDiaper(request: DiaperDto.Request) {

    }

    @PostMapping("/bath")
    fun addBath(request: BathDto.Request) {

    }

    @PostMapping("/sleep")
    fun addSleep(request: SleepDto.Request) {

    }

    @PostMapping("/health")
    fun addHealth(request: HealthDto.Request) {

    }

    @PostMapping("/snack")
    fun addSnack(request: SnackDto.Request) {

    }
}
