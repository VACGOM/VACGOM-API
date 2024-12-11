package kr.co.vacgom.api.user.repository

import kr.co.vacgom.api.user.User

interface UserRepository {
    fun save(user: User): User
    fun findBySocialId(socialId: String): User?
    fun findById(userId: Long): User?
    fun deleteById(userId: Long)
}
