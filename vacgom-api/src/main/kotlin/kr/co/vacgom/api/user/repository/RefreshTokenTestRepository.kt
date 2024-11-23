package kr.co.vacgom.api.user.repository

import org.springframework.stereotype.Repository
import java.util.*

@Repository
class RefreshTokenTestRepository(
    private val db: MutableMap<UUID, String> = mutableMapOf(),
): RefreshTokenRepository {
    override fun update(token: String, userId: UUID) {
        db[userId] = token
    }

    override fun save(token: String, userId: UUID) {
        db[userId] = token
    }

    override fun deleteByUserId(userId: UUID) {
        db.remove(userId)
    }

    override fun findAll(): Map<UUID, String> {
        return db.toMap()
    }
}
