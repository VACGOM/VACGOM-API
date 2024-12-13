package kr.co.vacgom.api.user.repository

import kr.co.vacgom.api.user.domain.RefreshToken
import java.util.*

interface RefreshTokenRepository {
    fun save(token: RefreshToken)
    fun deleteByUserId(userId: UUID)
}
