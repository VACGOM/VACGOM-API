package kr.co.vacgom.api.carehistoryitem.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType.BREAST_PUMPING
import kr.co.vacgom.api.global.util.UuidCreator
import org.hibernate.annotations.Comment
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "TB_BREAST_PUMPING")
class BreastPumping (
    id: UUID = UuidCreator.create(),
    amount: Int,
    itemType: CareHistoryItemType = BREAST_PUMPING,
    executionDate: LocalDateTime,
): CareHistoryItem(id, executionDate, itemType) {
    @Column(nullable = false)
    @Comment("[Not Null] 유축 양")
    var amount = amount
        protected set
}
