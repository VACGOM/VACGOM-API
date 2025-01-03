package kr.co.vacgom.api.user.application

import io.kotest.core.spec.style.DescribeSpec
import io.mockk.mockk
import kr.co.vacgom.api.baby.application.BabyCommandService
import kr.co.vacgom.api.babymanager.application.BabyManagerService
import kr.co.vacgom.api.user.repository.UserRepository

class UserServiceTest: DescribeSpec( {
    val userTokenServiceMock: UserTokenService = mockk(relaxed = true)
    val userRepositoryMock: UserRepository = mockk(relaxed = true)
    val authServiceMock: AuthService = mockk(relaxed = true)
    val babyCommandServiceMock: BabyCommandService = mockk(relaxed = true)
    val babyManagerServiceMock: BabyManagerService = mockk(relaxed = true)

    val sut = UserCommandService(
        userTokenServiceMock,
        userRepositoryMock,
        authServiceMock,
        babyCommandServiceMock,
        babyManagerServiceMock,
    )
})
