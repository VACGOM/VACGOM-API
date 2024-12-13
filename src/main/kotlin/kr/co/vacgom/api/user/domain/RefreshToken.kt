package kr.co.vacgom.api.user.domain

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.util.*

@RedisHash("refreshToken", timeToLive = 86400)
class RefreshToken(
    userId: UUID,
    refreshToken: String,
) {
    @Id
    var userId: UUID = userId
        private set

    var refreshToken: String = refreshToken
        private set
}
