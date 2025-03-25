package kr.co.vacgom.api.carehistoryitem.application

import kr.co.vacgom.api.baby.application.BabyQueryService
import kr.co.vacgom.api.carehistoryitem.domain.*
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import kr.co.vacgom.api.carehistoryitem.presentation.dto.*
import kr.co.vacgom.api.carehistoryitem.repository.CareHistoryItemRepository
import org.springframework.stereotype.Service
import java.time.temporal.ChronoUnit

@Service
class CareHistoryItemCommandService(
    private val careHistoryItemRepository: CareHistoryItemRepository,
    private val babyQueryService: BabyQueryService,
) {
    fun addBreastFeeding(request: BreastFeedingDto.Request) {
        val newBreastFeeding = BreastFeeding(
            leftStartTime = request.leftStartDate,
            leftEndTime = request.leftEndDate,
            leftMinutes = ChronoUnit.MINUTES.between(request.leftStartDate, request.leftEndDate).toInt(),
            rightStartTime = request.rightStartDate,
            rightEndTime = request.rightEndDate,
            rightMinutes = ChronoUnit.MINUTES.between(request.rightStartDate, request.rightEndDate).toInt(),
            baby = babyQueryService.getBabyById(request.babyId),
            executionTime = request.executionTime,
            itemType = CareHistoryItemType.BREAST_FEEDING
        )

        careHistoryItemRepository.createCareHistoryItem(newBreastFeeding)
    }

    fun addBabyFormula(request: BabyFormulaDto.Request) {
        val newBabyFormula = BabyFormula(
            amount = request.amount,
            baby = babyQueryService.getBabyById(request.babyId),
            executionTime = request.executionTime,
            itemType = CareHistoryItemType.BABY_FORMULA,
        )

        careHistoryItemRepository.createCareHistoryItem(newBabyFormula)
    }

    fun addBreastPumping(request: BreastPumpingDto.Request) {
        val newBreastPumping = BreastPumping(
            amount = request.amount,
            baby = babyQueryService.getBabyById(request.babyId),
            executionTime = request.executionTime,
            itemType = CareHistoryItemType.BREAST_PUMPING
        )

        careHistoryItemRepository.createCareHistoryItem(newBreastPumping)
    }

    fun addBabyFood(request: BabyFoodDto.Request) {
        val newBabyFood = BabyFood(
            amount = request.amount,
            baby = babyQueryService.getBabyById(request.babyId),
            executionTime = request.executionTime,
            itemType = CareHistoryItemType.BABY_FOOD
        )

        careHistoryItemRepository.createCareHistoryItem(newBabyFood)
    }

    fun addDiaper(request: DiaperDto.Request) {
        val newDiaper = Diaper(
            excrementType = request.excrementType,
            baby = babyQueryService.getBabyById(request.babyId),
            executionTime = request.executionTime,
            itemType = CareHistoryItemType.DIAPER,
        )

        careHistoryItemRepository.createCareHistoryItem(newDiaper)
    }

    fun addBath(request: BathDto.Request) {
        val minutes = ChronoUnit.MINUTES.between(request.startDate, request.endDate).toInt()

        val newBath = Bath(
            startTime = request.startDate,
            endTime = request.endDate,
            minutes = minutes,
            baby = babyQueryService.getBabyById(request.babyId),
            executionTime = request.executionTime,
            itemType = CareHistoryItemType.BATH
        )

        careHistoryItemRepository.createCareHistoryItem(newBath)
    }

    fun addSleep(request: SleepDto.Request) {
        val minutes = ChronoUnit.MINUTES.between(request.startDate, request.endDate).toInt()

        val newSleep = Sleep(
            startTime = request.startDate,
            endTime = request.endDate,
            minutes = minutes,
            baby = babyQueryService.getBabyById(request.babyId),
            executionTime = request.executionTime,
            itemType = CareHistoryItemType.SLEEP,
        )

        careHistoryItemRepository.createCareHistoryItem(newSleep)
    }

    fun addHealth(request: HealthDto.Request) {
        val newHealth = Health(
            temperature = request.temperature,
            memo = request.memo,
            baby = babyQueryService.getBabyById(request.babyId),
            executionTime = request.executionTime,
            itemType = CareHistoryItemType.HEALTH,
        )

        careHistoryItemRepository.createCareHistoryItem(newHealth)
    }

    fun addSnack(request: SnackDto.Request) {
        val newSnack = Snack(
            memo = request.memo,
            baby = babyQueryService.getBabyById(request.babyId),
            executionTime = request.executionTime,
            itemType = CareHistoryItemType.SNACK,
        )

        careHistoryItemRepository.createCareHistoryItem(newSnack)
    }
}
