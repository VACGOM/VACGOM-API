package kr.co.vacgom.api.carehistoryitem.application

import kr.co.vacgom.api.carehistoryitem.domain.*
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import kr.co.vacgom.api.carehistoryitem.presentation.dto.*
import kr.co.vacgom.api.carehistoryitem.repository.CareHistoryItemRepository
import org.springframework.stereotype.Service
import java.time.temporal.ChronoUnit

@Service
class CareHistoryItemCreateService(
    private val careHistoryItemRepository: CareHistoryItemRepository,
) {
    fun addBreastFeeding(request: BreastFeedingDto.Request) {
        val newBreastFeeding = BreastFeeding(
            startTime = request.startDate,
            endTime = request.endDate,
            minutes = ChronoUnit.MINUTES.between(request.startDate, request.endDate).toInt(),
            breastDirection = request.breastDirection,
            executionTime = request.executionTime,
            itemType = CareHistoryItemType.BREAST_FEEDING
        )

        careHistoryItemRepository.saveHistoryItem(newBreastFeeding)
    }

    fun addBabyFormula(request: BabyFormulaDto.Request) {
        val newBabyFormula = BabyFormula(
            amount = request.amount,
            executionTime = request.executionTime,
            itemType = CareHistoryItemType.BABY_FORMULA,
        )

        careHistoryItemRepository.saveHistoryItem(newBabyFormula)
    }

    fun addBreastPumping(request: BreastPumpingDto.Request) {
        val newBreastPumping = BreastPumping(
            amount = request.amount,
            executionTime = request.executionTime,
            itemType = CareHistoryItemType.BREAST_PUMPING
        )

        careHistoryItemRepository.saveHistoryItem(newBreastPumping)
    }

    fun addBabyFood(request: BabyFoodDto.Request) {
        val newBabyFood = BabyFood(
            amount = request.amount,
            executionTime = request.executionTime,
            itemType = CareHistoryItemType.BABY_FOOD
        )

        careHistoryItemRepository.saveHistoryItem(newBabyFood)
    }

    fun addDiaper(request: DiaperDto.Request) {
        val newDiaper = Diaper(
            excrementType = request.excrementType,
            executionTime = request.executionTime,
            itemType = CareHistoryItemType.DIAPER,
        )

        careHistoryItemRepository.saveHistoryItem(newDiaper)
    }

    fun addBath(request: BathDto.Request) {
        val minutes = ChronoUnit.MINUTES.between(request.startDate, request.endDate).toInt()

        val newBath = Bath(
            startTime = request.startDate,
            endTime = request.endDate,
            minutes = minutes,
            executionTime = request.executionTime,
            itemType = CareHistoryItemType.BATH
        )

        careHistoryItemRepository.saveHistoryItem(newBath)
    }

    fun addSleep(request: SleepDto.Request) {
        val minutes = ChronoUnit.MINUTES.between(request.startDate, request.endDate).toInt()

        val newSleep = Sleep(
            startTime = request.startDate,
            endTime = request.endDate,
            minutes = minutes,
            executionTime = request.executionTime,
            itemType = CareHistoryItemType.SLEEP,
        )

        careHistoryItemRepository.saveHistoryItem(newSleep)
    }

    fun addHealth(request: HealthDto.Request) {
        val newHealth = Health(
            temperature = request.temperature,
            memo = request.memo,
            executionTime = request.executionTime,
            itemType = CareHistoryItemType.HEALTH,
        )

        careHistoryItemRepository.saveHistoryItem(newHealth)
    }

    fun addSnack(request: SnackDto.Request) {
        val newSnack = Snack(
            memo = request.memo,
            executionTime = request.executionTime,
            itemType = CareHistoryItemType.SNACK,
        )

        careHistoryItemRepository.saveHistoryItem(newSnack)
    }
}
