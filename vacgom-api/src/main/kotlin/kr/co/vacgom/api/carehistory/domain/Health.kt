package kr.co.vacgom.api.carehistory.domain

import kr.co.vacgom.api.global.common.domain.BaseTimeEntity
import java.time.LocalDateTime

data class Health (
    val temperature: Double,
    val memo: String,
    val executionDate: LocalDateTime,
): BaseTimeEntity()
