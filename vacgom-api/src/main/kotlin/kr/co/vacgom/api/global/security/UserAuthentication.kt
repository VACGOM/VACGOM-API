package kr.co.vacgom.api.global.security

data class UserAuthentication(
    override val userId: Long,
): Authentication
