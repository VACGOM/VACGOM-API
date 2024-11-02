package kr.co.vacgom.api.member.repository

import kr.co.vacgom.api.auth.client.enums.SocialLoginProvider
import kr.co.vacgom.api.member.domain.Member
import org.springframework.stereotype.Repository

@Repository
class MemberTestRepository(
    private val db: MutableMap<Long, Member> = mutableMapOf(1L to Member(1L,
        socialId = "",
        provider = SocialLoginProvider.KAKAO,
        name = "name",
        id = "id",
        password = "password")),
): MemberRepository {
    override fun save(member: Member) {
        db[member.userId] = member
    }

    override fun findBySocialId(socialId: String): Member? {
        return db.filter { it.value.socialId == socialId }.values.firstOrNull()
    }

    override fun findByIdAndPassword(id: String, password: String): Member? {
        return db.filter { it.value.id == id && it.value.password == password }.values.firstOrNull()
    }

    override fun findByUserId(userId: Long): Member? {
        return db[userId]
    }
}
