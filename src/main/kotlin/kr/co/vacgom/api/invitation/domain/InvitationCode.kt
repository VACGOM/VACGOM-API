package kr.co.vacgom.api.invitation.domain

import java.util.*

data class InvitationCode(
    val key: String,
    val userId: UUID,
    val babyIds: List<UUID>,
    val timeToLive: Long = 86400L
)
