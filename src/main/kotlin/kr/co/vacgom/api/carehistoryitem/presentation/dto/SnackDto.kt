package kr.co.vacgom.api.carehistoryitem.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.vacgom.api.carehistoryitem.domain.BabyFood
import kr.co.vacgom.api.carehistoryitem.domain.Snack
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import kr.co.vacgom.api.carehistoryitem.presentation.dto.BabyFoodDto.Response.DailyDetail
import java.time.LocalDateTime
import java.util.*

class SnackDto {
    @Schema(name = "SnackDto.Request")
    data class Request(
        val babyId: UUID,
        val memo: String,
        val executionTime: LocalDateTime,
    )

    class Response {
        @Schema(name = "SnackDto.Response.DailyDetail")
        class DailyDetail(
            careName: String,
            val memo: String?,
            val executionTime: LocalDateTime,
        ): AbstractDailyDetailDto(careName) {
            companion object {
                fun of(item: Snack): DailyDetail {
                    return DailyDetail(careName = item.itemType.typeName, memo = item.memo, executionTime = item.executionTime)
                }
            }
        }

        @Schema(name = "SnackDto.Response.DailyStat")
        class DailyStat(
            careName: String,
            val count: Int,
        ): AbstractDailyStatDto(careName) {
            companion object {
                fun of(type: CareHistoryItemType, items: List<Snack>): DailyStat {
                    return DailyStat(type.typeName, count = items.size)
                }
            }
        }
    }
}
