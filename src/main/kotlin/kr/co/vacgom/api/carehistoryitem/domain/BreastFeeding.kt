package kr.co.vacgom.api.carehistoryitem.domain

import jakarta.persistence.*
import kr.co.vacgom.api.baby.domain.Baby
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType.BREAST_FEEDING
import kr.co.vacgom.api.global.util.UuidCreator
import org.hibernate.annotations.Comment
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "TB_BREAST_FEEDING")
class BreastFeeding (
    id: UUID = UuidCreator.create(),
    leftStartTime: LocalDateTime,
    leftEndTime: LocalDateTime,
    rightStartTime: LocalDateTime,
    rightEndTime: LocalDateTime,
    leftMinutes: Int = 0,
    rightMinutes: Int = 0,
    baby: Baby,
    itemType: CareHistoryItemType = BREAST_FEEDING,
    executionTime: LocalDateTime,
): CareHistoryItem(id, executionTime, itemType, baby) {
    @Column(nullable = false)
    @Comment("[Not Null] 모유수유(왼) 시작 시간")
    var leftStartTime: LocalDateTime = leftStartTime
        protected set

    @Column(nullable = false)
    @Comment("[Not Null] 모유수유(왼) 종료 시간")
    var leftEndTime: LocalDateTime = leftEndTime
        protected set

    @Column(nullable = false)
    @Comment("[Not Null] 모유수유(오) 시작 시간")
    var rightStartTime: LocalDateTime = leftStartTime
        protected set

    @Column(nullable = false)
    @Comment("[Not Null] 모유수유(오) 종료 시간")
    var rightEndTime: LocalDateTime = rightEndTime
        protected set

    @Column(nullable = false)
    @Comment("[Not Null] 모유수유 시간(분)")
    var leftMinutes: Int = leftMinutes
        protected set

    @Column(nullable = false)
    @Comment("[Not Null] 모유수유 시간(분)")
    var rightMinutes: Int = rightMinutes
        protected set
}
