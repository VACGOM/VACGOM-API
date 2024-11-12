package kr.co.vacgom.api.user.repository

import kr.co.vacgom.api.user.domain.RefreshToken

interface RefreshTokenRepository {
    fun update(newToken: String, userId: Long)
    fun save(token: String, userId: Long)
    fun deleteByUserId(userId: Long)
    fun findAll(): Map<Long, RefreshToken>
}
