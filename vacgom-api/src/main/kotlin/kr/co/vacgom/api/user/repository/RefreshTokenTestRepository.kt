package kr.co.vacgom.api.user.repository

import kr.co.vacgom.api.user.domain.RefreshToken
import org.springframework.stereotype.Repository

@Repository
class RefreshTokenTestRepository(
    private val db: MutableMap<Long, RefreshToken> = mutableMapOf(),
): RefreshTokenRepository {
    override fun update(newToken: String, userId: Long) {
        db[userId] = RefreshToken(newToken, userId)
    }

    override fun save(token: String, userId: Long) {
        db[userId] = RefreshToken(token, userId)
    }

    override fun deleteByUserId(userId: Long) {
        db.remove(userId)
    }

    override fun findAll(): Map<Long, RefreshToken> {
        return db.toMap()
    }
}
