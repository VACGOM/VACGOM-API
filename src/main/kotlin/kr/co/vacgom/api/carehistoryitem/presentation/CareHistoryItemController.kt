package kr.co.vacgom.api.carehistoryitem.presentation

import kr.co.vacgom.api.carehistoryitem.application.CareHistoryItemCreateService
import kr.co.vacgom.api.carehistoryitem.application.CareHistoryItemGetService
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import kr.co.vacgom.api.carehistoryitem.presentation.CareHistoryItemApi.Companion.CARE_HISTORY
import kr.co.vacgom.api.carehistoryitem.presentation.dto.*
import kr.co.vacgom.api.carehistoryitem.presentation.dto.enums.DateType
import kr.co.vacgom.api.global.common.dto.BaseResponse
import kr.co.vacgom.api.global.exception.error.BusinessException
import kr.co.vacgom.api.global.exception.error.GlobalError
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
    override fun getCareHistoryItemsByExecutionDate(
        @RequestParam babyId: UUID,
        @RequestParam executionDate: LocalDate,
        @RequestParam itemType: CareHistoryItemType?,
    ): BaseResponse<CareHistoryDto.Response> {
        return when (itemType) {
            null -> careHistoryItemGetService.getCareHistoryByExecutionDate(babyId, executionDate).let {
                BaseResponse.success(it)
            }
            else -> careHistoryItemGetService.getCareHistoryItemsByItemTypeAndExecutionDate(
                babyId,
                itemType,
                executionDate
            ).let { BaseResponse.success(it) }
        }
    }

    @GetMapping("/stats")
    override fun getCareHistoryStatsByInputDate(@ModelAttribute request: CareHistoryDto.Request.Stats): BaseResponse<CareHistoryDto.Response> {
        return when (request.dateType) {
            DateType.WEEKLY, DateType.MONTHLY -> {
                careHistoryItemGetService.getCareHistoryStatsWithChangeMetaDataByInputDate(
                    babyId = request.babyId,
                    dateType = request.dateType,
                    startDate = request.startDate,
                    endDate = request.endDate
                ).let { BaseResponse.success(it) }
            }

            else -> {
                throw BusinessException(GlobalError.GLOBAL_NOT_FOUND)
            }
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
