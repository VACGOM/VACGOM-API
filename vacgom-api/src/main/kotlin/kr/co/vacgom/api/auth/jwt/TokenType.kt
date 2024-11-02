package kr.co.vacgom.api.auth.jwt

enum class TokenType(val type: String) {
    ACCESS_TOKEN("access-token"),
    REFRESH_TOKEN("refresh-token"),
}
