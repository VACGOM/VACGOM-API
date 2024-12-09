package kr.co.vacgom.api.carehistoryitem.presentation.dto

import kr.co.vacgom.api.carehistoryitem.domain.*
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import kr.co.vacgom.api.global.util.snakeToCamelCase
import java.time.LocalDate
import java.util.*

class CareHistoryDto{
    class Response {
        data class Daily(
            val babyId: UUID,
            val executionDate: LocalDate,
            val careItems: Map<String, AbstractDailyStatDto>
        ) {
            companion object {
                fun of(careHistory: CareHistory): Daily {
                    val careItems = careHistory.careHistoryItems.map { item ->
                        val dailyItemValues = when (item.key) {
                            CareHistoryItemType.BREAST_FEEDING -> BreastFeedingDto.Response.DailyStat.of(
                                CareHistoryItemType.BREAST_FEEDING,
                                item.value.map { it as BreastFeeding }
                            )
                            CareHistoryItemType.BABY_FORMULA -> BabyFormulaDto.Response.DailyStat.of(
                                CareHistoryItemType.BABY_FORMULA,
                                item.value.map { it as BabyFormula }
                            )
                            CareHistoryItemType.BREAST_PUMPING -> BreastPumpingDto.Response.DailyStat.of(
                                CareHistoryItemType.BREAST_PUMPING,
                                item.value.map { it as BreastPumping }
                            )
                            CareHistoryItemType.BABY_FOOD -> BabyFoodDto.Response.DailyStat.of(
                                CareHistoryItemType.BABY_FOOD,
                                item.value.map { it as BabyFood }
                            )
                            CareHistoryItemType.SNACK -> SnackDto.Response.DailyStat.of(
                                CareHistoryItemType.SNACK,
                                item.value.map { it as Snack }
                            )
                            CareHistoryItemType.DIAPER -> DiaperDto.Response.DailyStat.of(
                                CareHistoryItemType.DIAPER,
                                item.value.map { it as Diaper }
                            )
                            CareHistoryItemType.BATH -> BathDto.Response.DailyStat.of(
                                CareHistoryItemType.BATH,
                                item.value.map { it as Bath }
                            )
                            CareHistoryItemType.HEALTH -> HealthDto.Response.DailyStat.of(
                                CareHistoryItemType.HEALTH,
                                item.value.map { it as Health }
                            )
                            CareHistoryItemType.SLEEP -> SleepDto.Response.DailyStat.of(
                                CareHistoryItemType.SLEEP,
                                item.value.map { it as Sleep }
                            )
                        }

                        item.key.name.lowercase().snakeToCamelCase() to dailyItemValues
                    }.toMap()

                    return Daily(
                        babyId = careHistory.babyId,
                        executionDate = careHistory.executionDate,
                        careItems = careItems,
                    )
                }
            }
        }
    }
}
