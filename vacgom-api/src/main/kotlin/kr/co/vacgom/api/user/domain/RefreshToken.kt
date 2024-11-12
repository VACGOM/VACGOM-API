package kr.co.vacgom.api.user.domain

data class RefreshToken(
    val refreshToken: String,
    val userId: Long,
)
