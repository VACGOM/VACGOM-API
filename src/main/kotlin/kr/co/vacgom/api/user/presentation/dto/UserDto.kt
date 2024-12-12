package kr.co.vacgom.api.user.presentation.dto

import kr.co.vacgom.api.auth.oauth.enums.SocialLoginProvider
import kr.co.vacgom.api.user.domain.enums.UserRole
import java.time.LocalDateTime
import java.util.*

class UserDto {
    class Response {
        data class UserDetail(
            val id: UUID,
            val nickname: String,
            val socialId: String?,
            val provider: SocialLoginProvider,
            val role: UserRole,
            val createdAt: LocalDateTime,
        )
    }
}

