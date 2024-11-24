package kr.co.vacgom.api.carehistory.domain

import kr.co.vacgom.api.global.common.domain.BaseTimeEntity
import kr.co.vacgom.api.global.util.UuidCreator
import java.time.LocalDate
import java.util.*

data class CareHistory (
    val id: UUID = UuidCreator.create(),
    val babyId: UUID,
    val recordDate: LocalDate,
    val babyFormulas: MutableList<BabyFormula>,
    val breastFeedings: MutableList<BreastFeeding>,
    val breastPumpings: MutableList<BreastPumping>,
    val babyFoods: MutableList<BabyFood>,
    val diapers: MutableList<Diaper>,
    val baths: MutableList<Bath>,
    val sleeps: MutableList<Sleep>,
    val healths: MutableList<Health>,
    val snacks: MutableList<Snack>,
): BaseTimeEntity() {

    fun addBabyFormula(item: BabyFormula) {
        babyFormulas.add(item)
    }

    fun addBreastFeeding(item: BreastFeeding) {
        breastFeedings.add(item)
    }

    fun addBreastPumping(item: BreastPumping) {
        breastPumpings.add(item)
    }

    fun addBabyFood(item: BabyFood) {
        babyFoods.add(item)
    }

    fun addDiaper(item: Diaper) {
        diapers.add(item)
    }

    fun addBath(item: Bath) {
        baths.add(item)
    }

    fun addSleep(item: Sleep) {
        sleeps.add(item)
    }

    fun addHealth(item: Health) {
        healths.add(item)
    }

    fun addSnack(item: Snack) {
        snacks.add(item)
    }

    companion object {
        fun create(
            babyId: UUID,
            recordDate: LocalDate,
            babyFormulas: MutableList<BabyFormula> = mutableListOf(),
            breastFeedings: MutableList<BreastFeeding> = mutableListOf(),
            breastPumpings: MutableList<BreastPumping> = mutableListOf(),
            babyFoods: MutableList<BabyFood> = mutableListOf(),
            diapers: MutableList<Diaper> = mutableListOf(),
            baths: MutableList<Bath> = mutableListOf(),
            sleeps: MutableList<Sleep> = mutableListOf(),
            healths: MutableList<Health> = mutableListOf(),
            snacks: MutableList<Snack> = mutableListOf(),
        ): CareHistory {
            return CareHistory(
                babyId = babyId,
                babyFormulas = babyFormulas,
                breastFeedings = breastFeedings,
                breastPumpings = breastPumpings,
                babyFoods = babyFoods,
                diapers = diapers,
                baths = baths,
                sleeps = sleeps,
                healths = healths,
                snacks = snacks,
                recordDate = recordDate
            )
        }
    }
}
