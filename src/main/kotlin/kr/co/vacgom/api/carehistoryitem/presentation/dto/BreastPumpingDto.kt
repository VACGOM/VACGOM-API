package kr.co.vacgom.api.carehistoryitem.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.vacgom.api.carehistoryitem.domain.BabyFood
import kr.co.vacgom.api.carehistoryitem.domain.BreastPumping
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import kr.co.vacgom.api.carehistoryitem.presentation.dto.BabyFoodDto.Response.DailyDetail
import kr.co.vacgom.api.carehistoryitem.presentation.dto.BreastFeedingDto.BreastFeedingStat
import java.time.LocalDateTime
import java.util.*

class BreastPumpingDto {
    @Schema(name = "BreastPumpingDto.Request")
    data class Request(
        val babyId: UUID,
        val amount: Int,
        val executionTime: LocalDateTime,
    )

    class Response {
        @Schema(name = "BreastPumpingDto.Response.DailyDetail")
        class DailyDetail(
            careName: String,
            val amount: Int,
            val executionTime: LocalDateTime,
        ): AbstractDailyDetailDto(careName) {
            companion object {
                fun of(item: BreastPumping): DailyDetail {
                    return DailyDetail(
                        careName = item.itemType.typeName,
                        amount = item.amount,
                        executionTime = item.executionTime
                    )
                }
            }
        }

        @Schema(name = "BreastPumpingDto.Response.DailyStat")
        class DailyStat(
            careName: String,
            val amount: Int,
        ): AbstractDailyStatDto(careName) {
            companion object {
                fun of(type: CareHistoryItemType, items: List<BreastPumping>): DailyStat {
                    return DailyStat(careName = type.typeName, amount = items.sumOf { it.amount })
                }
            }
        }
    }
}
