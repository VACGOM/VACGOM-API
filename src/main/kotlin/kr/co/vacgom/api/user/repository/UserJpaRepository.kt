package kr.co.vacgom.api.user.repository

import kr.co.vacgom.api.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository : JpaRepository<User, Long> {
    fun findBySocialId(socialId: String) : User?
}
