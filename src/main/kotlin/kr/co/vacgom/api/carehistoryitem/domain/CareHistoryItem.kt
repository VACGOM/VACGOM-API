package kr.co.vacgom.api.carehistoryitem.domain

import jakarta.persistence.*
import jakarta.persistence.InheritanceType.JOINED
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import kr.co.vacgom.api.global.common.domain.BaseTimeEntity
import org.hibernate.annotations.Comment
import java.time.LocalDateTime
import java.util.*

@Entity
@Inheritance(strategy = JOINED)
@Table(name = "TB_CARE_HISTORY_ITEM")
abstract class CareHistoryItem(
    id: UUID,
    executionTime: LocalDateTime,
    itemType: CareHistoryItemType,
): BaseTimeEntity() {
    @Id
    @Column(name = "care_history_item_id", nullable = false, updatable = false, columnDefinition = "BINARY(16)")
    @Comment("[Not Null] 육아 기록 아이템 id")
    var id = id
        protected set

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Comment("[Not Null] 기록 아이템 타입")
    var itemType: CareHistoryItemType = itemType
        protected set

    @Column(nullable = false)
    @Comment("[Not Null] 수행 시간")
    var executionTime: LocalDateTime = executionTime
        protected set

    //Todo(용현) BabyId 외래키 추가 필요
}
