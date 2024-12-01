package kr.co.vacgom.api.carehistoryitem.presentation.dto

import kr.co.vacgom.api.carehistoryitem.domain.Health
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import java.time.LocalDateTime
import java.util.*

class HealthDto {
    data class Request(
        val babyId: UUID,
        val temperature: Double,
        val memo: String,
        val executionDate: LocalDateTime,
    )

    class Response {
        class DailyStat(
            careName: String,
            val temperature: Double,
        ): AbstractDailyStatDto(careName) {
            companion object {
                fun of(type: CareHistoryItemType, items: List<Health>): DailyStat {
                    if (items.isEmpty()) {
                        return DailyStat(
                            careName = type.typeName,
                            temperature = 0.0
                        )
                    }

                    return DailyStat(
                        careName = type.typeName,
                        temperature = items.sumOf { it.temperature} / items.size
                    )
                }
            }
        }
    }
}
