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
        val findMember = userRepository.findByUserId(userId)
            ?: throw BusinessException(UserError.USER_NOT_FOUND)

        // Todo(자체 로그인, 소셜 로그인 구분에 따라 회원 탈퇴 로직 분기 필요)
        authService.unlinkUser(findMember)

        userRepository.deleteByUserId(userId)
    }
}
