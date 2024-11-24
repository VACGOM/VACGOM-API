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
        careHistoryEditService.addBreastFeeding(request)
    }

    @PostMapping("/baby-formula")
    fun addBabyFormula(request: BabyFormulaDto.Request) {
        careHistoryEditService.addBabyFormula(request)
    }

    @PostMapping("/breast-pumping")
    fun addBreastPumping(request: BreastPumpingDto.Request) {
        careHistoryEditService.addBreastPumping(request)
    }

    @PostMapping("/baby-food")
    fun addBabyFood(request: BabyFoodDto.Request) {
        careHistoryEditService.addBabyFood(request)
    }

    @PostMapping("/diaper")
    fun addDiaper(request: DiaperDto.Request) {
        careHistoryEditService.addDiaper(request)
    }

    @PostMapping("/bath")
    fun addBath(request: BathDto.Request) {
        careHistoryEditService.addBath(request)
    }

    @PostMapping("/sleep")
    fun addSleep(request: SleepDto.Request) {
        careHistoryEditService.addSleep(request)
    }

    @PostMapping("/health")
    fun addHealth(request: HealthDto.Request) {
        careHistoryEditService.addHealth(request)
    }

    @PostMapping("/snack")
    fun addSnack(request: SnackDto.Request) {
        careHistoryEditService.addSnack(request)
    }
}
