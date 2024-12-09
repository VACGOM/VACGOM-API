package kr.co.vacgom.api.carehistoryitem.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType.SLEEP
import kr.co.vacgom.api.global.util.UuidCreator
import org.hibernate.annotations.Comment
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "TB_SLEEP")
class Sleep (
    id: UUID = UuidCreator.create(),
    minutes: Int,
    startTime: LocalDateTime,
    endTime: LocalDateTime,
    itemType: CareHistoryItemType = SLEEP,
    executionTime: LocalDateTime,
): CareHistoryItem(id, executionTime, itemType) {
    @Column(nullable = false)
    @Comment("[Not Null] 수면 시작 시간")
    var startTime: LocalDateTime = startTime
        protected set

    @Column(nullable = false)
    @Comment("[Not Null] 수면 종료 시간")
    var endTime: LocalDateTime = endTime
        protected set

    @Column(nullable = false)
    @Comment("[Not Null] 수면 시간(분)")
    var minutes: Int = minutes
        protected set
}
