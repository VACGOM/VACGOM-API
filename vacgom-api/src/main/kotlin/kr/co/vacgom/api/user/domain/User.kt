package kr.co.vacgom.api.user.domain

import kr.co.vacgom.api.auth.client.enums.SocialLoginProvider

class User(
    val userId: Long,
    val socialId: String?,
    val provider: SocialLoginProvider,
    val name: String,
    val id: String,
    val password: String,
) {
}
