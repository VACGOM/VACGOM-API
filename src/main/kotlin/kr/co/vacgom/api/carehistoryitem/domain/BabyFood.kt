package kr.co.vacgom.api.carehistoryitem.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import kr.co.vacgom.api.baby.domain.Baby
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType.BABY_FOOD
import kr.co.vacgom.api.global.util.UuidCreator
import org.hibernate.annotations.Comment
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "TB_BABY_FOOD")
class BabyFood(
    id: UUID = UuidCreator.create(),
    amount: Int,
    baby: Baby,
    itemType: CareHistoryItemType = BABY_FOOD,
    executionTime: LocalDateTime,
) : CareHistoryItem(id, executionTime, itemType, baby) {
    @Column(nullable = false)
    @Comment("[Not Null] 이유식 양")
    var amount = amount
        protected set
}
