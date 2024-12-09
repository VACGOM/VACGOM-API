package kr.co.vacgom.api.carehistoryitem.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType.BATH
import kr.co.vacgom.api.global.util.UuidCreator
import lombok.Getter
import org.hibernate.annotations.Comment
import java.time.LocalDateTime
import java.util.*

@Entity
@Getter
@Table(name = "TB_BATH")
class Bath (
    id: UUID = UuidCreator.create(),
    startTime: LocalDateTime,
    endTime: LocalDateTime,
    minutes: Int,
    itemType: CareHistoryItemType = BATH,
    executionTime: LocalDateTime,
): CareHistoryItem(id, executionTime, itemType) {
    @Column(nullable = false)
    @Comment("[Not Null] 목욕 시작 시간")
    var startTime: LocalDateTime = startTime
        protected set

    @Column(nullable = false)
    @Comment("[Not Null] 목욕 종료 시간")
    var endTime: LocalDateTime = endTime
        protected set

    @Column(nullable = false)
    @Comment("[Not Null] 목욕 시간(분)")
    var minutes: Int = minutes
        protected set
}
