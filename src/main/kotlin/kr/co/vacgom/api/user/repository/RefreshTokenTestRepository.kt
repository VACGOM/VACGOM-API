package kr.co.vacgom.api.user.repository

import org.springframework.stereotype.Component

@Component
class RefreshTokenTestRepository(
    private val db: MutableMap<Long, String> = mutableMapOf(),
) {
    fun update(token: String, userId: Long) {
        db[userId] = token
    }

    fun save(token: String, userId: Long) {
        db[userId] = token
    }

    fun deleteByUserId(userId: Long) {
        db.remove(userId)
    }

    fun findAll(): Map<Long, String> {
        return db.toMap()
    }
}
