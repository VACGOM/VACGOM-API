package kr.co.vacgom.api.carehistoryitem.domain

import jakarta.persistence.Entity
import jakarta.persistence.Table
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType.HEALTH
import kr.co.vacgom.api.global.util.UuidCreator
import org.hibernate.annotations.Comment
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "TB_HEALTH")
class Health (
    id: UUID = UuidCreator.create(),
    temperature: Double,
    memo: String,
    itemType: CareHistoryItemType = HEALTH,
    executionTime: LocalDateTime,
): CareHistoryItem(id, executionTime, itemType) {
    @Comment("체온")
    var temperature: Double = temperature
        protected set

    @Comment("메모 내용")
    var memo: String = memo
        protected set
}
