package kr.co.vacgom.api.user.repository

import kr.co.vacgom.api.user.domain.User
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryAdapter(
    private val userJpaRepository: UserJpaRepository
): UserRepository {
    override fun save(user: User): User {
        return userJpaRepository.save(user)
    }

    override fun findBySocialId(socialId: String): User? {
        return userJpaRepository.findBySocialId(socialId)
    }

    override fun findById(userId: Long): User? {
        return userJpaRepository.findByIdOrNull(userId)
    }

    override fun deleteById(userId: Long) {
        userJpaRepository.deleteById(userId)
    }
}
