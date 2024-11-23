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
): BaseTimeEntity()
