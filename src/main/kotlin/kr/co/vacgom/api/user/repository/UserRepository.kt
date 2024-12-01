package kr.co.vacgom.api.user.repository

import kr.co.vacgom.api.user.domain.User
import java.util.*

interface UserRepository {
    fun save(user: User)
    fun findBySocialId(socialId: String): User?
    fun findByUserId(userId: UUID): User?
    fun deleteByUserId(userId: UUID)
}
