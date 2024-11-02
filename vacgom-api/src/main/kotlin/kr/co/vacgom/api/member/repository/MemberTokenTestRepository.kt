package kr.co.vacgom.api.member.repository

import kr.co.vacgom.api.member.domain.RefreshToken
import org.springframework.stereotype.Repository

@Repository
class MemberTokenTestRepository(
    private val db: MutableMap<Long, RefreshToken> = mutableMapOf(),
): MemberTokenRepository {
    override fun update(newToken: String, userId: Long) {
        db[userId] = RefreshToken(newToken, userId)
    }

    override fun save(token: String, userId: Long) {
        db[userId] = RefreshToken(token, userId)
    }

    override fun deleteTokenByUserId(userId: Long) {
        db.remove(userId)
    }

    override fun findAll(): Map<Long, RefreshToken> {
        return db.toMap()
    }
}
