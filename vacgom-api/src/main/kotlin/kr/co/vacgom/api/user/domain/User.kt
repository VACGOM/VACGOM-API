package kr.co.vacgom.api.user.domain

import kr.co.vacgom.api.auth.oauth.enums.SocialLoginProvider
import org.springframework.security.core.GrantedAuthority

class User(
    val id: Long,
    val socialId: String?,
    val provider: SocialLoginProvider,
    val name: String,
    val roles: List<GrantedAuthority>,
) {
}
