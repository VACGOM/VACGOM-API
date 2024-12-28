package kr.co.vacgom.api.user.application

import kr.co.vacgom.api.baby.application.BabyService
import kr.co.vacgom.api.baby.domain.Baby
import kr.co.vacgom.api.babymanager.application.BabyManagerService
import kr.co.vacgom.api.babymanager.domain.BabyManager
import kr.co.vacgom.api.global.exception.error.BusinessException
import kr.co.vacgom.api.s3.S3Service
import kr.co.vacgom.api.user.domain.User
import kr.co.vacgom.api.user.domain.enums.UserRole
import kr.co.vacgom.api.user.exception.UserError
import kr.co.vacgom.api.user.presentation.dto.SignupDto
import kr.co.vacgom.api.user.presentation.dto.UserDto
import kr.co.vacgom.api.user.repository.UserRepository
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
    private val babyManagerService: BabyManagerService,
    private val s3Service: S3Service,
) {
    fun signup(request: SignupDto.Request): SignupDto.Response {
        val registerToken = userTokenService.resolveRegisterToken(request.registerToken)

        val newUser = User(
            nickname = request.nickname,
            socialId = registerToken.socialId,
            provider = registerToken.provider,
            role = UserRole.ROLE_USER,
        )

        val newBabies = request.babies.map {
            Baby(
                name = it.name,
                profileImg = s3Service.uploadImage(it.profileImg),
                gender = it.gender,
                birthday = it.birthday,
            )
        }


        val savedUser = userRepository.save(newUser)
        val savedBabies = babyService.saveAll(newBabies)
        val managers = savedBabies.map { baby ->
            BabyManager(
                user = savedUser,
                baby = baby,
                isAdmin = true
            )
        }

        babyManagerService.saveAll(managers)

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

    @Transactional(readOnly = true)
    fun getUserById(userId: UUID): User {
        return userRepository.findById(userId) ?: throw BusinessException(UserError.USER_NOT_FOUND)
    }
}
