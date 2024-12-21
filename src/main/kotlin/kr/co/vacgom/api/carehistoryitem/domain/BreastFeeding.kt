package kr.co.vacgom.api.carehistoryitem.domain

import jakarta.persistence.*
import kr.co.vacgom.api.baby.domain.Baby
import kr.co.vacgom.api.carehistoryitem.domain.enums.BreastDirection
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
    startTime: LocalDateTime,
    endTime: LocalDateTime,
    minutes: Int,
    breastDirection: BreastDirection,
    baby: Baby,
    itemType: CareHistoryItemType = BREAST_FEEDING,
    executionTime: LocalDateTime,
): CareHistoryItem(id, executionTime, itemType, baby) {
    @Column(nullable = false)
    @Comment("[Not Null] 모유수유 시작 시간")
    var startTime: LocalDateTime = startTime
        protected set

    @Column(nullable = false)
    @Comment("[Not Null] 모유수유 종료 시간")
    var endTime: LocalDateTime = endTime
        protected set

    @Column(nullable = false)
    @Comment("[Not Null] 모유수유 시간(분)")
    var minutes: Int = minutes
        protected set

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Comment("[Not Null] breast 방향")
    var breastDirection: BreastDirection = breastDirection
        protected set
}
