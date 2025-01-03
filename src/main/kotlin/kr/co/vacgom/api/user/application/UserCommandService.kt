package kr.co.vacgom.api.user.application

import kr.co.vacgom.api.baby.application.BabyCommandService
import kr.co.vacgom.api.baby.domain.Baby
import kr.co.vacgom.api.babymanager.application.BabyManagerService
import kr.co.vacgom.api.babymanager.domain.BabyManager
import kr.co.vacgom.api.global.exception.error.BusinessException
import kr.co.vacgom.api.user.domain.User
import kr.co.vacgom.api.user.domain.enums.UserRole
import kr.co.vacgom.api.user.exception.UserError
import kr.co.vacgom.api.user.presentation.dto.SignupDto
import kr.co.vacgom.api.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class UserCommandService(
    private val userTokenService: UserTokenService,
    private val userRepository: UserRepository,
    private val authService: AuthService,
    private val babyCommandService: BabyCommandService,
    private val babyManagerService: BabyManagerService,
) {
    fun signup(request: SignupDto.Request): SignupDto.Response {
        val registerToken = userTokenService.resolveRegisterToken(request.registerToken)

        val savedUser = User(
            nickname = request.nickname,
            socialId = registerToken.socialId,
            provider = registerToken.provider,
            role = UserRole.ROLE_USER,
        ).let { userRepository.save(it) }

        request.babies
            .map {
                Baby(
                    name = it.name,
                    profileImg = it.profileImg,
                    gender = it.gender,
                    birthday = it.birthday,
                )
            }
            .also { babyCommandService.saveAll(it) }
            .mapIndexed { index, baby ->
                BabyManager(
                    user = savedUser,
                    baby = baby,
                    isAdmin = request.babies[index].isAdmin,
                )
            }
            .let { babyManagerService.saveAll(it) }

        val refreshToken = userTokenService.createRefreshToken(savedUser.id)
        userTokenService.saveRefreshToken(refreshToken, savedUser.id)

        return SignupDto.Response(
            accessToken = userTokenService.createAccessToken(savedUser.id, savedUser.role),
            refreshToken = refreshToken,
        )
    }

    fun revoke(userId: UUID) {
        val findUser = userRepository.findById(userId)
            ?: throw BusinessException(UserError.USER_NOT_FOUND)

        authService.unlinkUser(findUser)
        userRepository.deleteById(userId)
    }
}
