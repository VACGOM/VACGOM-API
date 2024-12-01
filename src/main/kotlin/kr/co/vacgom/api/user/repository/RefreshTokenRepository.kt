package kr.co.vacgom.api.user.repository

import java.util.*

interface RefreshTokenRepository {
    fun update(token: String, userId: UUID)
    fun save(token: String, userId: UUID)
    fun deleteByUserId(userId: UUID)
    fun findAll(): Map<UUID, String>
}
