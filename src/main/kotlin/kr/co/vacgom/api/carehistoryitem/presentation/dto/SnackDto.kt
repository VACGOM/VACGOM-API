package kr.co.vacgom.api.carehistoryitem.presentation.dto

import kr.co.vacgom.api.carehistoryitem.domain.Snack
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import java.time.LocalDateTime
import java.util.*

class SnackDto {
    data class Request(
        val babyId: UUID,
        val memo: String,
        val executionDate: LocalDateTime,
    )

    class Response {
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
