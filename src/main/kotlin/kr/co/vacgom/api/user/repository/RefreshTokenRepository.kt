package kr.co.vacgom.api.user.repository

interface RefreshTokenRepository {
    fun update(token: String, userId: Long)
    fun save(token: String, userId: Long)
    fun deleteByUserId(userId: Long)
    fun findAll(): Map<Long, String>
}
