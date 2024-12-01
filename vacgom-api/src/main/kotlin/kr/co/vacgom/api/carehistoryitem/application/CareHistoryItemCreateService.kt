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
        val newBreastFeeding = BreastFeeding.create(
            startDate = request.startDate,
            endDate = request.endDate,
            minutes = ChronoUnit.MINUTES.between(request.startDate, request.endDate).toInt(),
            breastDirection = request.breastDirection,
            executionDate = request.executionDate,
            itemType = CareHistoryItemType.BREAST_FEEDING
        )

        careHistoryItemRepository.saveHistoryItem(newBreastFeeding)
    }

    fun addBabyFormula(request: BabyFormulaDto.Request) {
        val newBabyFormula = BabyFormula.create(
            amount = request.amount,
            executionDate = request.executionDate,
            itemType = CareHistoryItemType.BABY_FORMULA,
        )

        careHistoryItemRepository.saveHistoryItem(newBabyFormula)
    }

    fun addBreastPumping(request: BreastPumpingDto.Request) {
        val newBreastPumping = BreastPumping.create(
            amount = request.amount,
            executionDate = request.executionDate,
            itemType = CareHistoryItemType.BREAST_PUMPING
        )

        careHistoryItemRepository.saveHistoryItem(newBreastPumping)
    }

    fun addBabyFood(request: BabyFoodDto.Request) {
        val newBabyFood = BabyFood.create(
            amount = request.amount,
            executionDate = request.executionDate,
            itemType = CareHistoryItemType.BABY_FOOD
        )

        careHistoryItemRepository.saveHistoryItem(newBabyFood)
    }

    fun addDiaper(request: DiaperDto.Request) {
        val newDiaper = Diaper.create(
            excrementType = request.excrementType,
            executionDate = request.executionDate,
            itemType = CareHistoryItemType.DIAPER,
        )

        careHistoryItemRepository.saveHistoryItem(newDiaper)
    }

    fun addBath(request: BathDto.Request) {
        val minutes = ChronoUnit.MINUTES.between(request.startDate, request.endDate).toInt()

        val newBath = Bath.create(
            startDate = request.startDate,
            endDate = request.endDate,
            minutes = minutes,
            executionDate = request.executionDate,
            itemType = CareHistoryItemType.BATH
        )

        careHistoryItemRepository.saveHistoryItem(newBath)
    }

    fun addSleep(request: SleepDto.Request) {
        val minutes = ChronoUnit.MINUTES.between(request.startDate, request.endDate).toInt()

        val newSleep = Sleep.create(
            startDate = request.startDate,
            endDate = request.endDate,
            minutes = minutes,
            executionDate = request.executionDate,
            itemType = CareHistoryItemType.SLEEP,
        )

        careHistoryItemRepository.saveHistoryItem(newSleep)
    }

    fun addHealth(request: HealthDto.Request) {
        val newHealth = Health.create(
            temperature = request.temperature,
            memo = request.memo,
            executionDate = request.executionDate,
            itemType = CareHistoryItemType.HEALTH,
        )

        careHistoryItemRepository.saveHistoryItem(newHealth)
    }

    fun addSnack(request: SnackDto.Request) {
        val newSnack = Snack.create(
            memo = request.memo,
            executionDate = request.executionDate,
            itemType = CareHistoryItemType.SNACK,
        )

        careHistoryItemRepository.saveHistoryItem(newSnack)
    }
}
