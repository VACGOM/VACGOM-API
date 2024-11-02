package kr.co.vacgom.api.member.domain

data class RefreshToken(
    val refreshToken: String,
    val userId: Long,
)
