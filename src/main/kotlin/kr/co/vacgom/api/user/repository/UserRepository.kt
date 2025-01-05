package kr.co.vacgom.api.user.repository

import kr.co.vacgom.api.user.domain.User
import java.util.*


interface UserRepository {
    fun save(user: User): User
    fun findBySocialId(socialId: String): User?
    fun findById(userId: UUID): User?
    fun deleteById(userId: UUID)
    fun findAll(): List<User>
}
