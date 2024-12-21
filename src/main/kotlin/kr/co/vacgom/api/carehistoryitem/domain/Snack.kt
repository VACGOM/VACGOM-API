package kr.co.vacgom.api.carehistoryitem.domain

import jakarta.persistence.Entity
import jakarta.persistence.Table
import kr.co.vacgom.api.baby.domain.Baby
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType.SNACK
import kr.co.vacgom.api.global.util.UuidCreator
import org.hibernate.annotations.Comment
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "TB_SNACK")
class Snack (
    id: UUID = UuidCreator.create(),
    memo: String,
    baby: Baby,
    itemType: CareHistoryItemType = SNACK,
    executionTime: LocalDateTime,
): CareHistoryItem(id, executionTime, itemType, baby) {
    @Comment("메모")
    var memo: String? = memo
        protected set
}
