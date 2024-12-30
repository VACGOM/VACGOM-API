package kr.co.vacgom.api.carehistoryitem.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema
import kr.co.vacgom.api.carehistoryitem.domain.Diaper
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import kr.co.vacgom.api.carehistoryitem.domain.enums.ExcrementType
import java.time.LocalDateTime
import java.util.*

class DiaperDto {
    @Schema(name = "DiaperDto.Request")
    data class Request(
        val babyId: UUID,
        val excrementType: ExcrementType,
        val executionTime: LocalDateTime,
    )

    class Response {
        @Schema(name = "Diaper.Response.DailyStat")
        class DailyStat(
            careName: String,
            val count: Int,
        ): AbstractDailyStatDto(careName) {
            companion object {
                fun of(type: CareHistoryItemType, items: List<Diaper>): DailyStat {
                    return DailyStat(type.typeName, count = items.size)
                }
            }
        }
    }


}

