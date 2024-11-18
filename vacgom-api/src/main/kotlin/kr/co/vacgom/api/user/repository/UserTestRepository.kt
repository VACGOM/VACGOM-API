package kr.co.vacgom.api.user.repository

import kr.co.vacgom.api.auth.oauth.enums.SocialLoginProvider
import kr.co.vacgom.api.user.domain.User
import org.springframework.stereotype.Repository

@Repository
class UserTestRepository(
    private val db: MutableMap<Long, User> = mutableMapOf(1L to User(1L,
        socialId = "",
        provider = SocialLoginProvider.KAKAO,
        nickname = "nickname",
        roles = emptyList(),
        )
    ),
): UserRepository {
    override fun save(user: User) {
        db[user.id] = user
    }

    override fun findBySocialId(socialId: String): User? {
        return db.filter { it.value.socialId == socialId }.values.firstOrNull()
    }

    override fun findByUserId(userId: Long): User? {
        return db[userId]
    }

    override fun deleteByUserId(userId: Long) {
        db.remove(userId)
    }
}
