package kr.co.vacgom.api.carehistory.domain

import kr.co.vacgom.api.global.common.domain.BaseTimeEntity
import java.time.LocalDateTime

data class Sleep (
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
): BaseTimeEntity()
