package kr.co.vacgom.api.user.application

import kr.co.vacgom.api.baby.application.BabyService
import kr.co.vacgom.api.global.exception.error.BusinessException
import kr.co.vacgom.api.user.domain.Baby
import kr.co.vacgom.api.baby.ManagerGroup
import kr.co.vacgom.api.user.domain.User
import kr.co.vacgom.api.user.domain.enums.UserRole
import kr.co.vacgom.api.user.exception.UserError
import kr.co.vacgom.api.user.presentation.dto.Signup
import kr.co.vacgom.api.user.repository.RefreshTokenRepository
import kr.co.vacgom.api.user.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class UserService(
    private val userTokenService: UserTokenService,
    private val userRepository: UserRepository,
    private val authService: AuthService,
    private val babyService: BabyService,
    private val refreshTokenRepository: RefreshTokenRepository,
) {
    fun signup(request: Signup.Request): Signup.Response {
        val registerToken = userTokenService.resolveRegisterToken(request.registerToken)

        val newUser = User.create(
            nickname = request.nickname,
            socialId = registerToken.socialId,
            provider = registerToken.provider,
            roles = arrayListOf(SimpleGrantedAuthority(UserRole.ROLE_USER.name)),
        )

        val newBabies = request.babies.map {
            Baby.create(
                name = it.name,
                profileImg = it.profileImgUrl,
                gender = it.gender,
                birthday = it.birthday,
                managerGroup = ManagerGroup.create(
                    adminManager = newUser,
                    managers = mutableSetOf(newUser),
                )
            )
        }

        newUser.addBabies(newBabies.toSet())

        val refreshToken = userTokenService.createRefreshToken(newUser.id)
        refreshTokenRepository.save(refreshToken, newUser.id)
        babyService.saveAll(newBabies)
        userRepository.save(newUser)

        return Signup.Response(
            accessToken = userTokenService.createAccessToken(newUser.id, newUser.roles),
            refreshToken = refreshToken,
        )
    }

    fun revoke(userId: UUID) {
        val findUser = userRepository.findByUserId(userId)
            ?: throw BusinessException(UserError.USER_NOT_FOUND)

        authService.unlinkUser(findUser)
        userRepository.deleteByUserId(userId)
    }
}
