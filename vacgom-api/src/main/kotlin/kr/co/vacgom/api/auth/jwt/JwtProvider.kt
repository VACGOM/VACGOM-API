package kr.co.vacgom.api.auth.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTDecodeException
import com.auth0.jwt.exceptions.TokenExpiredException
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.security.SignatureException
import kr.co.vacgom.api.auth.jwt.exception.JwtError
import kr.co.vacgom.api.global.exception.error.BusinessException
import org.springframework.stereotype.Component

@Component
class JwtProvider {
    fun createToken(payload: JwtPayload, secret: String): String {
        return JWT.create()
            .withJWTId(payload.jti).withSubject(payload.sub)
            .withIssuer(payload.iss).withAudience(*payload.aud?.toTypedArray() ?: arrayOf())
            .withIssuedAt(payload.iat).withNotBefore(payload.nbf)
            .withExpiresAt(payload.exp).withPayload(payload.privateClaims)
            .sign(Algorithm.HMAC256(secret))
    }

    fun verifyToken(token: String, secret: String): Result<JwtPayload> {
        val algorithm: Algorithm = Algorithm.HMAC256(secret)
        return verifyToken(token, algorithm)
    }

    private fun verifyToken(
        token: String,
        algorithm: Algorithm,
    ): Result<JwtPayload> {
        return runCatching {
            JWT.require(algorithm).build().verify(token)
        }.mapCatching { claims ->
            JwtPayload.from(claims)
        }.onFailure { exception ->
            handleException(exception)
        }
    }

    private fun handleException(ex: Throwable): Nothing {
        exceptionHandlerList.first { it.first.isAssignableFrom(ex::class.java) }.second.invoke(ex)
    }

    private val exceptionHandlerList = listOf<Pair<Class<out Throwable>, (Throwable) -> Nothing>>(
        JWTDecodeException::class.java to { throw BusinessException(JwtError.MALFORMED_JWT) },
        TokenExpiredException::class.java to { throw BusinessException(JwtError.EXPIRED_JWT) },
        SignatureException::class.java to {throw BusinessException(JwtError.SIGNATURE)},
        UnsupportedJwtException::class.java to { throw BusinessException(JwtError.UNSUPPORTED_JWT) },
        Throwable::class.java to { throw BusinessException(JwtError.JWT_EXCEPTION) },
    )
}
