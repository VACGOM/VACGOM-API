package kr.co.vacgom.api.carehistoryitem.presentation

import kr.co.vacgom.api.carehistoryitem.application.CareHistoryItemCreateService
import kr.co.vacgom.api.carehistoryitem.application.CareHistoryItemGetService
import kr.co.vacgom.api.carehistoryitem.presentation.CareHistoryItemApi.Companion.CARE_HISTORY
import kr.co.vacgom.api.carehistoryitem.presentation.dto.*
import kr.co.vacgom.api.global.common.dto.BaseResponse
import kr.co.vacgom.api.global.presentation.GlobalPath.BASE_V3
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.util.*

@RestController
@RequestMapping(BASE_V3 + CARE_HISTORY)
class CareHistoryItemController(
    private val careHistoryItemCreateService: CareHistoryItemCreateService,
    private val careHistoryItemGetService: CareHistoryItemGetService
): CareHistoryItemApi {
    @GetMapping
    override fun getCareHistoryByExecutionDate(
        @RequestParam babyId: UUID,
        @RequestParam executionDate: LocalDate,
    ): BaseResponse<CareHistoryDto.Response.Daily> {
        return BaseResponse.success {
            careHistoryItemGetService.getCareHistoryByExecutionDate(babyId, executionDate)
        }
    }

    @PostMapping("/breast-feeding")
    override fun addBreastFeeding(@RequestBody request: BreastFeedingDto.Request) {
        careHistoryItemCreateService.addBreastFeeding(request)
    }

    @PostMapping("/baby-formula")
    override fun addBabyFormula(@RequestBody request: BabyFormulaDto.Request) {
        careHistoryItemCreateService.addBabyFormula(request)
    }

    @PostMapping("/breast-pumping")
    override fun addBreastPumping(@RequestBody request: BreastPumpingDto.Request) {
        careHistoryItemCreateService.addBreastPumping(request)
    }

    @PostMapping("/baby-food")
    override fun addBabyFood(@RequestBody request: BabyFoodDto.Request) {
        careHistoryItemCreateService.addBabyFood(request)
    }

    @PostMapping("/diaper")
    override fun addDiaper(@RequestBody request: DiaperDto.Request) {
        careHistoryItemCreateService.addDiaper(request)
    }

    @PostMapping("/bath")
    override fun addBath(@RequestBody request: BathDto.Request) {
        careHistoryItemCreateService.addBath(request)
    }

    @PostMapping("/sleep")
    override  fun addSleep(@RequestBody request: SleepDto.Request) {
        careHistoryItemCreateService.addSleep(request)
    }

    @PostMapping("/health")
    override fun addHealth(@RequestBody request: HealthDto.Request) {
        careHistoryItemCreateService.addHealth(request)
    }

    @PostMapping("/snack")
    override fun addSnack(@RequestBody request: SnackDto.Request) {
        careHistoryItemCreateService.addSnack(request)
    }
}
