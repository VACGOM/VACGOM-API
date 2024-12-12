package kr.co.vacgom.api.user.repository

import kr.co.vacgom.api.user.domain.RefreshToken
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class RefreshTokenRepositoryAdapter(
    private val refreshTokenRedisRepository: RefreshTokenRedisRepository
): RefreshTokenRepository {
    override fun save(token: RefreshToken) {
        refreshTokenRedisRepository.save(token)
    }

    override fun deleteByUserId(userId: UUID) {
        refreshTokenRedisRepository.deleteById(userId)
    }
}
