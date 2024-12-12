package kr.co.vacgom.api.user.repository

import kr.co.vacgom.api.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserJpaRepository : JpaRepository<User, UUID> {
    fun findBySocialId(socialId: String) : User?
}
