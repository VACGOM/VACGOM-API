package kr.co.vacgom.api.auth.jwt

import com.auth0.jwt.RegisteredClaims
import com.auth0.jwt.interfaces.DecodedJWT
import java.time.Instant
import java.util.*
import kotlin.reflect.full.declaredMembers

data class JwtPayload(
    val iss: String,
    val aud: List<String>? = null,
    val sub: String,
    val exp: Date = Date.from(Instant.now().plusSeconds(DEFAULT_EXPIRATION_SEC)),
    val nbf: Date = Date.from(Instant.now()),
    val iat: Date = Date.from(Instant.now()),
    val jti: String? = null,
    val privateClaims: MutableMap<String, Any> = mutableMapOf(),
) {
    companion object {

        const val DEFAULT_EXPIRATION_SEC: Long = 60 * 10 // = 10분

        fun from(decodedJwt: DecodedJWT): JwtPayload {
            val customClaims = mutableMapOf<String, Any>()

            decodedJwt.claims
                .filter { claim ->
                    claim.key !in RegisteredClaims::class.declaredMembers
                        .map {
                            RegisteredClaims::class.java.getDeclaredField(it.name).get(String::class)
                        }
                }
                .forEach { (key, value) ->
                    customClaims[key] = value
                }

            return JwtPayload(
                iss = decodedJwt.issuer,
                aud = decodedJwt.audience,
                sub = decodedJwt.subject,
                exp = decodedJwt.expiresAt,
                iat = decodedJwt.issuedAt,
                nbf = decodedJwt.notBefore,
                jti = decodedJwt.id,
                privateClaims = customClaims,
            )
        }
    }
}
