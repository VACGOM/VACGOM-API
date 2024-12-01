package kr.co.vacgom.api.carehistoryitem.domain

import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import kr.co.vacgom.api.global.common.domain.BaseTimeEntity
import java.time.LocalDateTime

abstract class CareHistoryItem(
    val executionDate: LocalDateTime,
    val itemType: CareHistoryItemType
): BaseTimeEntity()
