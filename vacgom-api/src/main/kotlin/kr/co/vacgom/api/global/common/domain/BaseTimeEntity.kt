package kr.co.vacgom.api.global.common.domain

import java.time.LocalDateTime

abstract class BaseTimeEntity(
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now(),
)
