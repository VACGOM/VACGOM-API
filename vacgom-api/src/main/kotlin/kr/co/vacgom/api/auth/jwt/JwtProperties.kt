package kr.co.vacgom.api.auth.jwt

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix="jwt")
class JwtProperties(
    val issuer: String,
    val secret: String,
    val accessTokenExpirationSec: Long,
    val refreshTokenExpirationSec: Long,
    val registerTokenExpirationSec: Long,
)
