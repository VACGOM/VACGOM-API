package kr.co.vacgom.api.member.service

import com.auth0.jwt.interfaces.Claim
import jakarta.servlet.http.HttpServletRequest
import kr.co.vacgom.api.auth.jwt.JwtPayload
import kr.co.vacgom.api.auth.jwt.JwtProperties
import kr.co.vacgom.api.auth.jwt.JwtProvider
import kr.co.vacgom.api.auth.jwt.TokenType
import kr.co.vacgom.api.member.repository.MemberTokenRepository
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class MemberTokenService(
    private val jwtTokenProvider: JwtProvider,
    private val jwtProperties: JwtProperties,
    private val memberTokenRepository: MemberTokenRepository
) {
    fun createAccessToken(userId: Long): String {
        val jwtPayLoad = JwtPayload(
                iss = jwtProperties.issuer,
                sub = TokenType.ACCESS_TOKEN.name,
                exp = Date.from(Instant.now().plusSeconds(jwtProperties.accessTokenExpirationSec)),
                privateClaims = mutableMapOf("userId" to userId)
            )

        return jwtTokenProvider.createToken(jwtPayLoad, jwtProperties.secret)
    }

    fun createRefreshToken(userId: Long): String {
        val jwtPayLoad = JwtPayload(
            iss = jwtProperties.issuer,
            sub = TokenType.REFRESH_TOKEN.name,
            exp = Date.from(Instant.now().plusSeconds(jwtProperties.refreshTokenExpirationSec)),
            privateClaims = mutableMapOf("userId" to userId)
        )

        val refreshToken = jwtTokenProvider.createToken(jwtPayLoad, jwtProperties.secret)
        memberTokenRepository.save(refreshToken, userId)

        return refreshToken
    }

    fun createRegisterToken(socialId: String): String {
        val jwtPayLoad = JwtPayload(
            iss = jwtProperties.issuer,
            sub = TokenType.REGISTER_TOKEN.name,
            exp = Date.from(Instant.now().plusSeconds(jwtProperties.registerTokenExpirationSec)),
            privateClaims = mutableMapOf("socialId" to socialId)
        )

        return jwtTokenProvider.createToken(jwtPayLoad, jwtProperties.secret)
    }

    fun resolveToken(token: String): Long {
        val jwtPayload = jwtTokenProvider.verifyToken(token, jwtProperties.secret).getOrThrow()
        val userIdClaim = jwtPayload.privateClaims["userId"] as Claim
        return userIdClaim.asLong()
    }

    fun reIssueAccessToken(refreshToken: String): String {
        val userId = resolveToken(refreshToken)
        return createAccessToken(userId)
    }

    fun extractToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(TOKEN_HEADER)

        return if (bearerToken != null && bearerToken.startsWith(BEARER_PREFIX)) {
            bearerToken.substring(BEARER_PREFIX.length)
        } else {
            null
        }
    }

    fun deleteRefreshToken(userId: Long) {
        memberTokenRepository.deleteTokenByUserId(userId)
    }

    companion object {
        const val TOKEN_HEADER = "Authorization"
        const val BEARER_PREFIX = "Bearer "
    }
}
