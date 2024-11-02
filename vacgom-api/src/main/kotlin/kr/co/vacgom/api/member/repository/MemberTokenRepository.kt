package kr.co.vacgom.api.member.repository

import kr.co.vacgom.api.member.domain.RefreshToken

interface MemberTokenRepository {
    fun update(newToken: String, userId: Long)
    fun save(token: String, userId: Long)
    fun deleteTokenByUserId(userId: Long)
    fun findAll(): Map<Long, RefreshToken>
}
