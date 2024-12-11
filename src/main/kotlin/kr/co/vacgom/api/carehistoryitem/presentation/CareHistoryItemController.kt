package kr.co.vacgom.api.carehistoryitem.presentation

import kr.co.vacgom.api.carehistoryitem.application.CareHistoryItemCreateService
import kr.co.vacgom.api.carehistoryitem.application.CareHistoryItemGetService
import kr.co.vacgom.api.carehistoryitem.presentation.CareHistoryItemPath.CARE_HISTORY
import kr.co.vacgom.api.carehistoryitem.presentation.dto.*
import kr.co.vacgom.api.global.presentation.GlobalPath.BASE_V3
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.util.*

@RestController
@RequestMapping(BASE_V3 + CARE_HISTORY)
class CareHistoryItemController(
    private val careHistoryItemCreateService: CareHistoryItemCreateService,
    private val careHistoryItemGetService: CareHistoryItemGetService
) {
    @GetMapping
    fun getCareHistoryByExecutionDate(
        @RequestParam babyId: UUID,
        @RequestParam executionDate: LocalDate,
    ): CareHistoryDto.Response.Daily {
        return careHistoryItemGetService.getCareHistoryByExecutionDate(babyId, executionDate)
    }

    @PostMapping("/breast-feeding")
    fun addBreastFeeding(@RequestBody request: BreastFeedingDto.Request) {
        careHistoryItemCreateService.addBreastFeeding(request)
    }

    @PostMapping("/baby-formula")
    fun addBabyFormula(@RequestBody request: BabyFormulaDto.Request) {
        careHistoryItemCreateService.addBabyFormula(request)
    }

    @PostMapping("/breast-pumping")
    fun addBreastPumping(@RequestBody request: BreastPumpingDto.Request) {
        careHistoryItemCreateService.addBreastPumping(request)
    }

    @PostMapping("/baby-food")
    fun addBabyFood(@RequestBody request: BabyFoodDto.Request) {
        careHistoryItemCreateService.addBabyFood(request)
    }

    @PostMapping("/diaper")
    fun addDiaper(@RequestBody request: DiaperDto.Request) {
        careHistoryItemCreateService.addDiaper(request)
    }

    @PostMapping("/bath")
    fun addBath(@RequestBody request: BathDto.Request) {
        careHistoryItemCreateService.addBath(request)
    }

    @PostMapping("/sleep")
    fun addSleep(@RequestBody request: SleepDto.Request) {
        careHistoryItemCreateService.addSleep(request)
    }

    @PostMapping("/health")
    fun addHealth(@RequestBody request: HealthDto.Request) {
        careHistoryItemCreateService.addHealth(request)
    }

    @PostMapping("/snack")
    fun addSnack(@RequestBody request: SnackDto.Request) {
        careHistoryItemCreateService.addSnack(request)
    }
}
