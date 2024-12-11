package kr.co.vacgom.api.user.application.dto

import kr.co.vacgom.api.auth.oauth.enums.SocialLoginProvider
import org.springframework.security.core.GrantedAuthority
import java.util.*

sealed class UserTokenClaims {
    data class AccessTokenClaims(
        val userId: Long,
        val authorities: List<GrantedAuthority>
    )

    data class RefreshTokenClaims(
        val userId: Long,
    )

    data class RegisterTokenClaims(
        val socialId: String,
        val provider: SocialLoginProvider
    )
}
