package kr.co.vacgom.api.user.repository

import kr.co.vacgom.api.user.domain.RefreshToken
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
interface RefreshTokenRedisRepository: CrudRepository<RefreshToken, UUID>
