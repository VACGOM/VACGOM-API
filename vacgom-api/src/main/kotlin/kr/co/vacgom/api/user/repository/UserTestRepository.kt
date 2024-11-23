package kr.co.vacgom.api.user.repository

import kr.co.vacgom.api.auth.oauth.enums.SocialLoginProvider
import kr.co.vacgom.api.user.domain.User
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UserTestRepository(
    private val db: MutableList<User> = mutableListOf(User(
        socialId = "",
        provider = SocialLoginProvider.KAKAO,
        nickname = "nickname",
        roles = emptyList(),
        )
    ),
): UserRepository {
    override fun save(user: User) {
        db.add(user)
    }

    override fun findBySocialId(socialId: String): User? {
        return db.firstOrNull { it.socialId == socialId }
    }

    override fun findByUserId(userId: UUID): User? {
        return db.firstOrNull { it.id == userId }
    }

    override fun deleteByUserId(userId: UUID) {
        db.removeIf { it.id == userId }
    }
}
