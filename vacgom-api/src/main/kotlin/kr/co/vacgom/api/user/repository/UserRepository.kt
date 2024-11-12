package kr.co.vacgom.api.user.repository

import kr.co.vacgom.api.user.domain.User

interface UserRepository {
    fun save(user: User)
    fun findBySocialId(socialId: String): User?
    fun findByUserId(userId: Long): User?
    fun deleteByUserId(userId: Long)
}
