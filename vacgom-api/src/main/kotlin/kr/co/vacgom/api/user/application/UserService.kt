package kr.co.vacgom.api.user.application

import kr.co.vacgom.api.global.exception.error.BusinessException
import kr.co.vacgom.api.user.exception.UserError
import kr.co.vacgom.api.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val authService: AuthService,
) {
    fun signup() {
        // Todo(멤버 저장 & 토큰 리턴 구현 필요)
    }

    fun revoke(userId: Long) {
        val findUser = userRepository.findByUserId(userId)
            ?: throw BusinessException(UserError.USER_NOT_FOUND)

        authService.unlinkUser(findUser)
        userRepository.deleteByUserId(userId)
    }
}
