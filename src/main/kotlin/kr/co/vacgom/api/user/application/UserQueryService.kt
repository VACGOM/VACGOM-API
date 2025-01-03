package kr.co.vacgom.api.user.application

import kr.co.vacgom.api.global.exception.error.BusinessException
import kr.co.vacgom.api.user.domain.User
import kr.co.vacgom.api.user.exception.UserError
import kr.co.vacgom.api.user.presentation.dto.UserDto
import kr.co.vacgom.api.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class UserQueryService(
    private val userRepository: UserRepository,
) {
    fun getUserDetail(userId: UUID): UserDto.Response.UserDetail {
        return userRepository.findById(userId)?.let {
            UserDto.Response.UserDetail(
                id = it.id,
                nickname = it.nickname,
                socialId = it.socialId,
                provider = it.provider,
                role = it.role,
                createdAt = it.createdAt,
            )
        } ?: throw BusinessException(UserError.USER_NOT_FOUND)
    }

    fun getUserById(userId: UUID): User {
        return userRepository.findById(userId) ?: throw BusinessException(UserError.USER_NOT_FOUND)
    }
}
