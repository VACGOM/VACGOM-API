package kr.co.vacgom.api.user.application

import com.auth0.jwt.interfaces.Claim
import jakarta.servlet.http.HttpServletRequest
import kr.co.vacgom.api.auth.jwt.JwtPayload
import kr.co.vacgom.api.auth.jwt.JwtProperties
import kr.co.vacgom.api.auth.jwt.JwtProvider
import kr.co.vacgom.api.auth.jwt.TokenType
import kr.co.vacgom.api.auth.oauth.enums.SocialLoginProvider
import kr.co.vacgom.api.global.exception.error.BusinessException
import kr.co.vacgom.api.user.application.dto.UserTokenClaims
import kr.co.vacgom.api.user.exception.UserError
import kr.co.vacgom.api.user.repository.RefreshTokenRepository
import kr.co.vacgom.api.user.repository.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class UserTokenService(
    private val jwtProvider: JwtProvider,
    private val jwtProperties: JwtProperties,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val userRepository: UserRepository,
) {
    fun createAccessToken(userId: UUID, authorities: List<GrantedAuthority>): String {
        val jwtPayLoad = JwtPayload(
                iss = jwtProperties.issuer,
                sub = TokenType.ACCESS_TOKEN.name,
                exp = Date.from(Instant.now().plusSeconds(jwtProperties.accessTokenExpirationSec)),
                privateClaims = mutableMapOf(
                    "userId" to userId.toString(),
                    "scope" to authorities.map { it.authority }.toList(),
                )
            )

        return jwtProvider.createToken(jwtPayLoad, jwtProperties.secret)
    }

    fun createRefreshToken(userId: UUID): String {
        val jwtPayLoad = JwtPayload(
            iss = jwtProperties.issuer,
            sub = TokenType.REFRESH_TOKEN.name,
            exp = Date.from(Instant.now().plusSeconds(jwtProperties.refreshTokenExpirationSec)),
            privateClaims = mutableMapOf(
                "userId" to userId.toString()
            )
        )

        val refreshToken = jwtProvider.createToken(jwtPayLoad, jwtProperties.secret)
        refreshTokenRepository.save(refreshToken, userId)

        return refreshToken
    }

    fun createRegisterToken(socialId: String, provider: String): String {
        val jwtPayLoad = JwtPayload(
            iss = jwtProperties.issuer,
            sub = TokenType.REGISTER_TOKEN.name,
            exp = Date.from(Instant.now().plusSeconds(jwtProperties.registerTokenExpirationSec)),
            privateClaims = mutableMapOf(
                "socialId" to socialId,
                "provider" to provider,
            )
        )

        return jwtProvider.createToken(jwtPayLoad, jwtProperties.secret)
    }

    fun resolveAccessToken(token: String): UserTokenClaims.AccessTokenClaims {
        val jwtPayload = jwtProvider.verifyToken(token, jwtProperties.secret).getOrThrow()
        val userId = jwtPayload.privateClaims["userId"] as Claim

        return UserTokenClaims.AccessTokenClaims(
            userId = UUID.fromString(userId.asString()),
            authorities = (jwtPayload.privateClaims["scope"] as Claim).asList(SimpleGrantedAuthority::class.java)
        )
    }

    fun resolveRefreshToken(token: String): UserTokenClaims.RefreshTokenClaims {
        val jwtPayload = jwtProvider.verifyToken(token, jwtProperties.secret).getOrThrow()
        val userId = jwtPayload.privateClaims["userId"] as Claim

        return UserTokenClaims.RefreshTokenClaims(
            userId = UUID.fromString(userId.asString()),
        )
    }

    fun resolveRegisterToken(token: String): UserTokenClaims.RegisterTokenClaims {
        val jwtPayload = jwtProvider.verifyToken(token, jwtProperties.secret).getOrThrow()
        val socialId = jwtPayload.privateClaims["socialId"] as Claim
        val provider = jwtPayload.privateClaims["provider"] as Claim

        return UserTokenClaims.RegisterTokenClaims(
            socialId = socialId.asString(),
            provider = SocialLoginProvider.parse(provider.asString())
        )
    }

    fun reIssueAccessToken(token: String): String {
        val refreshToken = resolveRefreshToken(token)
        val findUser = userRepository.findByUserId(refreshToken.userId)
            ?: throw BusinessException(UserError.USER_NOT_FOUND)

        return createAccessToken(findUser.id, findUser.roles)
    }

    fun extractToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(TOKEN_HEADER)

        return if (bearerToken != null && bearerToken.startsWith(BEARER_PREFIX)) {
            bearerToken.substring(BEARER_PREFIX.length)
        } else {
            null
        }
    }

    fun deleteRefreshToken(userId: UUID) {
        refreshTokenRepository.deleteByUserId(userId)
    }

    companion object {
        const val TOKEN_HEADER = "Authorization"
        const val BEARER_PREFIX = "Bearer "
    }
}
