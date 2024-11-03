package kr.co.vacgom.api.member.domain

import kr.co.vacgom.api.auth.client.enums.SocialLoginProvider

class Member(
    val userId: Long,
    val socialId: String?,
    val provider: SocialLoginProvider,
    val name: String,
    val id: String,
    val password: String,
) {
}
