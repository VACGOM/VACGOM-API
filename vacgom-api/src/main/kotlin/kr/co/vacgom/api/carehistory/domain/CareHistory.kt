package kr.co.vacgom.api.carehistory.domain

import kr.co.vacgom.api.global.common.domain.BaseTimeEntity
import kr.co.vacgom.api.global.util.UuidCreator
import java.time.LocalDateTime
import java.util.*

data class CareHistory (
    val id: UUID = UuidCreator.create(),
    val babyId: UUID,
    val babyFormulas: List<BabyFormula>,
    val breastFeedings: List<BreastFeeding>,
    val breastPumpings: List<BreastPumping>,
    val babyFoods: List<BabyFood>,
    val diapers: List<Diaper>,
    val baths: List<Bath>,
    val sleeps: List<Sleep>,
    val healths: List<Health>,
    val snacks: List<Snack>,
    val recordDate: LocalDateTime,
): BaseTimeEntity() {
    companion object {
        fun create(
            babyId: UUID,
            babyFormulas: List<BabyFormula>,
            breastFeedings: List<BreastFeeding>,
            breastPumpings: List<BreastPumping>,
            babyFoods: List<BabyFood>,
            diapers: List<Diaper>,
            baths: List<Bath>,
            sleeps: List<Sleep>,
            healths: List<Health>,
            snacks: List<Snack>,
            recordDate: LocalDateTime,
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
