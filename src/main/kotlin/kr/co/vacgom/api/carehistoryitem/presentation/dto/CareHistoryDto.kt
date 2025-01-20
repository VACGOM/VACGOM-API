package kr.co.vacgom.api.carehistoryitem.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.vacgom.api.carehistoryitem.domain.*
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import kr.co.vacgom.api.carehistoryitem.presentation.dto.enums.DateType
import kr.co.vacgom.api.global.util.snakeToCamelCase
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*

class CareHistoryDto{
    class Request {
        @Schema(name = "CareHistoryDto.Request.StatsRequest")
        class Stats(
            val babyId: UUID,
            val dateType: DateType,
            val startDate: LocalDate,
            val endDate: LocalDate,
        )
    }
    sealed class Response {
        @Schema(name = "CareHistoryDto.Response.FixedDateStats")
        data class FixedDateStats(
            val babyId: UUID,
            val startDate: LocalDate,
            val endDate: LocalDate,
            val careItems: Map<String, AbstractStatDto>
        ): Response() {
            companion object {
                fun of(
                    babyId: UUID,
                    startDate: LocalDate,
                    endDate: LocalDate,
                    beforeCareHistory: CareHistory,
                    nowCareHistory: CareHistory
                ) : FixedDateStats {
                    val careItems = enumValues<CareHistoryItemType>().associate { itemType ->
                        val beforeCareItems = beforeCareHistory.careHistoryItems[itemType] ?: emptyList()
                        val nowCareItems = nowCareHistory.careHistoryItems[itemType] ?: emptyList()
                        val dayCount = ChronoUnit.DAYS.between(startDate, endDate).toInt() + 1

                        val itemValue = when (itemType) {
                            CareHistoryItemType.BABY_FOOD -> BabyFoodDto.Response.FixedDateStats.of(
                                type = CareHistoryItemType.BABY_FOOD,
                                dayCount = dayCount,
                                beforeItems = beforeCareItems.map { it as BabyFood },
                                nowItems = nowCareItems.map { it as BabyFood }
                            )

                            CareHistoryItemType.BABY_FORMULA -> BabyFormulaDto.Response.FixedDateStats.of(
                                type = CareHistoryItemType.BABY_FORMULA,
                                dayCount = dayCount,
                                beforeItems = beforeCareItems.map { it as BabyFormula },
                                nowItems = nowCareItems.map { it as BabyFormula }
                            )

                            CareHistoryItemType.BATH -> BathDto.Response.FixedDateStats.of(
                                type = CareHistoryItemType.BATH,
                                dayCount = dayCount,
                                beforeItems = beforeCareItems.map { it as Bath },
                                nowItems = nowCareItems.map { it as Bath }
                            )

                            CareHistoryItemType.BREAST_FEEDING -> BreastFeedingDto.Response.FixedDateStats.of(
                                type = CareHistoryItemType.BREAST_FEEDING,
                                dayCount = dayCount,
                                beforeItems = beforeCareItems.map { it as BreastFeeding },
                                nowItems = nowCareItems.map { it as BreastFeeding }
                            )

                            CareHistoryItemType.BREAST_PUMPING -> BreastPumpingDto.Response.FixedDateStats.of(
                                type = CareHistoryItemType.BREAST_PUMPING,
                                dayCount = dayCount,
                                beforeItems = beforeCareItems.map { it as BreastPumping },
                                nowItems = nowCareItems.map { it as BreastPumping }
                            )

                            CareHistoryItemType.DIAPER -> DiaperDto.Response.FixedDateStats.of(
                                type = CareHistoryItemType.DIAPER,
                                dayCount = dayCount,
                                beforeItems = beforeCareItems.map { it as Diaper },
                                nowItems = nowCareItems.map { it as Diaper }
                            )

                            CareHistoryItemType.HEALTH -> HealthDto.Response.FixedDateStats.of(
                                type = CareHistoryItemType.HEALTH,
                                dayCount = dayCount,
                                beforeItems = beforeCareItems.map { it as Health },
                                nowItems = nowCareItems.map { it as Health }
                            )

                            CareHistoryItemType.SLEEP -> SleepDto.Response.FixedDateStats.of(
                                type = CareHistoryItemType.SLEEP,
                                dayCount = dayCount,
                                beforeItems = beforeCareItems.map { it as Sleep },
                                nowItems = nowCareItems.map { it as Sleep }
                            )

                            CareHistoryItemType.SNACK -> SnackDto.Response.FixedDateStats.of(
                                type = CareHistoryItemType.SNACK,
                                dayCount = dayCount,
                                beforeItems = beforeCareItems.map { it as Snack },
                                nowItems = nowCareItems.map { it as Snack }
                            )
                        }

                        itemType.name.lowercase().snakeToCamelCase() to itemValue
                    }

                    return FixedDateStats(
                        babyId = babyId,
                        startDate = startDate,
                        endDate = endDate,
                        careItems = careItems
                    )
                }
            }
        }

        @Schema(name = "CareHistoryDto.Response.CareHistoryItemDaily")
        data class DailyDetail(
            val babyId: UUID,
            val executionDate: LocalDate,
            val historyMetadata: AbstractStatDto,
            val historyItems: List<AbstractDetailDto>
        ): Response() {
            companion object {
                fun of(babyId: UUID, executionDate: LocalDate, itemType: CareHistoryItemType, items: List<CareHistoryItem>): DailyDetail {
                    
                    val historyItems = when (itemType) {
                        CareHistoryItemType.BREAST_FEEDING -> items.map { BreastFeedingDto.Response.Detail.of(it as BreastFeeding) }
                        CareHistoryItemType.BABY_FORMULA -> items.map { BabyFormulaDto.Response.Detail.of(it as BabyFormula) }
                        CareHistoryItemType.BREAST_PUMPING -> items.map { BreastPumpingDto.Response.Detail.of(it as BreastPumping) }
                        CareHistoryItemType.BABY_FOOD -> items.map { BabyFoodDto.Response.Detail.of(it as BabyFood) }
                        CareHistoryItemType.SNACK -> items.map { SnackDto.Response.Detail.of(it as Snack) }
                        CareHistoryItemType.DIAPER -> items.map { DiaperDto.Response.Detail.of(it as Diaper) }
                        CareHistoryItemType.BATH -> items.map { BathDto.Response.Detail.of(it as Bath) }
                        CareHistoryItemType.HEALTH -> items.map { HealthDto.Response.Detail.of(it as Health) }
                        CareHistoryItemType.SLEEP -> items.map { SleepDto.Response.Detail.of(it as Sleep) }
                    }

                    return DailyDetail(
                        babyId = babyId,
                        executionDate = executionDate,
                        historyMetadata = convertDailyStatDtoByItemType(itemType, items),
                        historyItems = historyItems
                    )
                }
            }
        }

        @Schema(name = "CareHistoryDto.Response.DailyStat")
        data class DailyStats(
            val babyId: UUID,
            val executionDate: LocalDate,
            val careItems: Map<String, AbstractStatDto>
        ): Response() {
            companion object {
                fun of(careHistory: CareHistory): DailyStats {
                    val careItems = enumValues<CareHistoryItemType>().associate { itemType ->
                        val items = careHistory.careHistoryItems[itemType] ?: emptyList()

                        val dailyItemValue = convertDailyStatDtoByItemType(itemType, items)

                        itemType.name.lowercase().snakeToCamelCase() to dailyItemValue
                    }

                    return DailyStats(
                        babyId = careHistory.babyId,
                        executionDate = careHistory.startTime.toLocalDate(),
                        careItems = careItems,
                    )
                }
            }
        }

        companion object {
            private fun convertDailyStatDtoByItemType(
                itemType: CareHistoryItemType,
                items: List<CareHistoryItem>,
            ) = when (itemType) {
                CareHistoryItemType.BREAST_FEEDING -> BreastFeedingDto.Response.DailyStats.of(
                    CareHistoryItemType.BREAST_FEEDING,
                    items.map { it as BreastFeeding }
                )

                CareHistoryItemType.BABY_FORMULA -> BabyFormulaDto.Response.DailyStats.of(
                    CareHistoryItemType.BABY_FORMULA,
                    items.map { it as BabyFormula }
                )

                CareHistoryItemType.BREAST_PUMPING -> BreastPumpingDto.Response.DailyStats.of(
                    CareHistoryItemType.BREAST_PUMPING,
                    items.map { it as BreastPumping }
                )

                CareHistoryItemType.BABY_FOOD -> BabyFoodDto.Response.DailyStats.of(
                    CareHistoryItemType.BABY_FOOD,
                    items.map { it as BabyFood }
                )

                CareHistoryItemType.SNACK -> SnackDto.Response.DailyStats.of(
                    CareHistoryItemType.SNACK,
                    items.map { it as Snack }
                )

                CareHistoryItemType.DIAPER -> DiaperDto.Response.DailyStats.of(
                    CareHistoryItemType.DIAPER,
                    items.map { it as Diaper }
                )

                CareHistoryItemType.BATH -> BathDto.Response.DailyStats.of(
                    CareHistoryItemType.BATH,
                    items.map { it as Bath }
                )

                CareHistoryItemType.HEALTH -> HealthDto.Response.DailyStats.of(
                    CareHistoryItemType.HEALTH,
                    items.map { it as Health }
                )

                CareHistoryItemType.SLEEP -> SleepDto.Response.DailyStats.of(
                    CareHistoryItemType.SLEEP,
                    items.map { it as Sleep }
                )
            }
        }
    }
}
