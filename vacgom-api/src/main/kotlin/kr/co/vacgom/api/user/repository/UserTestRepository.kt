package kr.co.vacgom.api.user.repository

import kr.co.vacgom.api.auth.oauth.enums.SocialLoginProvider
import kr.co.vacgom.api.user.domain.User
import org.springframework.stereotype.Repository

@Repository
class UserTestRepository(
    private val db: MutableMap<Long, User> = mutableMapOf(1L to User(1L,
        socialId = "3774375517",
        provider = SocialLoginProvider.KAKAO,
        name = "name",
        id = "id",
        password = "password")),
): UserRepository {
    override fun save(user: User) {
        db[user.userId] = user
    }

    override fun findBySocialId(socialId: String): User? {
        return db.filter { it.value.socialId == socialId }.values.firstOrNull()
    }

    override fun findByIdAndPassword(id: String, password: String): User? {
        return db.filter { it.value.id == id && it.value.password == password }.values.firstOrNull()
    }

    override fun findByUserId(userId: Long): User? {
        return db[userId]
    }

    override fun deleteByUserId(userId: Long) {
        db.remove(userId)
    }
}
