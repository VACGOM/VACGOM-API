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
                    val careItems = enumValues<CareHistoryItemType>().associate { itemType ->
                        val items = careHistory.careHistoryItems[itemType] ?: emptyList()

                        val dailyItemValue = when (itemType) {
                            CareHistoryItemType.BREAST_FEEDING -> BreastFeedingDto.Response.DailyStat.of(
                                CareHistoryItemType.BREAST_FEEDING,
                                items.map { it as BreastFeeding }
                            )

                            CareHistoryItemType.BABY_FORMULA -> BabyFormulaDto.Response.DailyStat.of(
                                CareHistoryItemType.BABY_FORMULA,
                                items.map { it as BabyFormula }
                            )

                            CareHistoryItemType.BREAST_PUMPING -> BreastPumpingDto.Response.DailyStat.of(
                                CareHistoryItemType.BREAST_PUMPING,
                                items.map { it as BreastPumping }
                            )

                            CareHistoryItemType.BABY_FOOD -> BabyFoodDto.Response.DailyStat.of(
                                CareHistoryItemType.BABY_FOOD,
                                items.map { it as BabyFood }
                            )

                            CareHistoryItemType.SNACK -> SnackDto.Response.DailyStat.of(
                                CareHistoryItemType.SNACK,
                                items.map { it as Snack }
                            )

                            CareHistoryItemType.DIAPER -> DiaperDto.Response.DailyStat.of(
                                CareHistoryItemType.DIAPER,
                                items.map { it as Diaper }
                            )

                            CareHistoryItemType.BATH -> BathDto.Response.DailyStat.of(
                                CareHistoryItemType.BATH,
                                items.map { it as Bath }
                            )

                            CareHistoryItemType.HEALTH -> HealthDto.Response.DailyStat.of(
                                CareHistoryItemType.HEALTH,
                                items.map { it as Health }
                            )

                            CareHistoryItemType.SLEEP -> SleepDto.Response.DailyStat.of(
                                CareHistoryItemType.SLEEP,
                                items.map { it as Sleep }
                            )
                        }

                        itemType.name.lowercase().snakeToCamelCase() to dailyItemValue
                    }

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
