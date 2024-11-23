package kr.co.vacgom.api.global.common.domain

import java.time.LocalDateTime

open class BaseTimeEntity(
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now(),
)
