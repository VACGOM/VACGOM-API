package kr.co.vacgom.api.carehistory.application

import kr.co.vacgom.api.carehistory.domain.*
import kr.co.vacgom.api.carehistory.presentation.dto.*
import kr.co.vacgom.api.carehistory.repository.CareHistoryRepository
import org.springframework.stereotype.Service
import java.time.temporal.ChronoUnit

@Service
class CareHistoryEditService(
    private val careHistoryRepository: CareHistoryRepository,
) {
    fun addBreastFeeding(request: BreastFeedingDto.Request) {
        val careHistory = careHistoryRepository.findByRecordDate(request.recordDate)
            ?: CareHistory.create(babyId = request.babyId, recordDate = request.recordDate)

        val newBreastFeeding = BreastFeeding.create(
            startDate = request.startDate,
            endDate = request.endDate,
            duration = ChronoUnit.MINUTES.between(request.startDate, request.endDate),
            breastDirection = request.breastDirection
        )

        careHistory.addBreastFeeding(newBreastFeeding)
        careHistoryRepository.save(careHistory)
    }

    fun addBabyFormula(request: BabyFormulaDto.Request) {
        val careHistory = careHistoryRepository.findByRecordDate(request.recordDate)
            ?: CareHistory.create(babyId = request.babyId, recordDate = request.recordDate)

        val newBabyFormula = BabyFormula.create(
            amount = request.amount,
            executionDate = request.executionDate,
        )

        careHistory.addBabyFormula(newBabyFormula)
        careHistoryRepository.save(careHistory)
    }

    fun addBreastPumping(request: BreastPumpingDto.Request) {
        val careHistory = careHistoryRepository.findByRecordDate(request.recordDate)
            ?: CareHistory.create(babyId = request.babyId, recordDate = request.recordDate)

        val newBreastPumping = BreastPumping.create(
            amount = request.amount,
            executionDate = request.executionDate,
        )

        careHistory.addBreastPumping(newBreastPumping)
        careHistoryRepository.save(careHistory)
    }

    fun addBabyFood(request: BabyFoodDto.Request) {
        val careHistory = careHistoryRepository.findByRecordDate(request.recordDate)
            ?: CareHistory.create(babyId = request.babyId, recordDate = request.recordDate)

        val newBabyFood = BabyFood.create(
            amount = request.amount,
            executionDate = request.executionDate,
        )

        careHistory.addBabyFood(newBabyFood)
        careHistoryRepository.save(careHistory)
    }

    fun addDiaper(request: DiaperDto.Request) {
        val careHistory = careHistoryRepository.findByRecordDate(request.recordDate)
            ?: CareHistory.create(babyId = request.babyId, recordDate = request.recordDate)

        val newDiaper = Diaper.create(
            excrementType = request.excrementType,
            executionDate = request.executionDate,
        )

        careHistory.addDiaper(newDiaper)
        careHistoryRepository.save(careHistory)
    }

    fun addBath(request: BathDto.Request) {
        val careHistory = careHistoryRepository.findByRecordDate(request.recordDate)
            ?: CareHistory.create(babyId = request.babyId, recordDate = request.recordDate)

        val newBath = Bath.create(
            startDate = request.startDate,
            endDate = request.endDate,
        )

        careHistory.addBath(newBath)
        careHistoryRepository.save(careHistory)
    }

    fun addSleep(request: SleepDto.Request) {
        val careHistory = careHistoryRepository.findByRecordDate(request.recordDate)
            ?: CareHistory.create(babyId = request.babyId, recordDate = request.recordDate)

        val newSleep = Sleep.create(
            startDate = request.startDate,
            endDate = request.endDate,
        )

        careHistory.addSleep(newSleep)
        careHistoryRepository.save(careHistory)
    }

    fun addHealth(request: HealthDto.Request) {
        val careHistory = careHistoryRepository.findByRecordDate(request.recordDate)
            ?: CareHistory.create(babyId = request.babyId, recordDate = request.recordDate)

        val newHealth = Health.create(
            temperature = request.temperature,
            memo = request.memo,
            executionDate = request.executionDate,
        )

        careHistory.addHealth(newHealth)
        careHistoryRepository.save(careHistory)
    }

    fun addSnack(request: SnackDto.Request) {
        val careHistory = careHistoryRepository.findByRecordDate(request.recordDate)
            ?: CareHistory.create(babyId = request.babyId, recordDate = request.recordDate)

        val newSnack = Snack.create(
            memo = request.memo,
            executionDate = request.executionDate,
        )

        careHistory.addSnack(newSnack)
        careHistoryRepository.save(careHistory)
    }
}
