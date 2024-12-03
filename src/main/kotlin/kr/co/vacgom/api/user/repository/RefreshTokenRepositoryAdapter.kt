package kr.co.vacgom.api.user.repository

import org.springframework.stereotype.Repository

@Repository
class RefreshTokenRepositoryAdapter(
    private val refreshTokenTestRepository: RefreshTokenTestRepository
): RefreshTokenRepository {
    override fun update(token: String, userId: Long) {
        refreshTokenTestRepository.update(token, userId)
    }

    override fun save(token: String, userId: Long) {
        refreshTokenTestRepository.save(token, userId)
    }

    override fun deleteByUserId(userId: Long) {
        refreshTokenTestRepository.deleteByUserId(userId)
    }

    override fun findAll(): Map<Long, String> {
        return refreshTokenTestRepository.findAll()
    }
}
