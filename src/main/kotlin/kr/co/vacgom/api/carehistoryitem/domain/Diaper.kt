package kr.co.vacgom.api.carehistoryitem.domain

import jakarta.persistence.*
import kr.co.vacgom.api.baby.domain.Baby
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType.DIAPER
import kr.co.vacgom.api.carehistoryitem.domain.enums.ExcrementType
import kr.co.vacgom.api.global.util.UuidCreator
import org.hibernate.annotations.Comment
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "TB_DIAPER")
class Diaper (
    id: UUID = UuidCreator.create(),
    excrementType: ExcrementType,
    baby: Baby,
    itemType: CareHistoryItemType = DIAPER,
    executionTime: LocalDateTime,
): CareHistoryItem(id, executionTime, itemType, baby) {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Comment("[Not Null] 대소변 타입")
    var excrementType: ExcrementType = excrementType
        protected set
}
