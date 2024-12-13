package kr.co.vacgom.api.invitation.domain

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.util.*

@RedisHash(value = "invitation_code", timeToLive = 86400)
class InvitationCode(
    code: UUID,
    babyIds: Set<UUID>,
) {
    @Id
    var code: UUID = code
        private set

    var babyIds: Set<UUID> = babyIds
        private set
}
