package kr.co.vacgom.api.user.application

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import io.mockk.every
import io.mockk.mockk
import kr.co.vacgom.api.auth.jwt.exception.JwtError
import kr.co.vacgom.api.baby.application.BabyService
import kr.co.vacgom.api.global.exception.error.BusinessException
import kr.co.vacgom.api.user.domain.enums.Gender
import kr.co.vacgom.api.user.presentation.dto.Signup
import kr.co.vacgom.api.user.repository.RefreshTokenRepository
import kr.co.vacgom.api.user.repository.UserRepository
import java.time.LocalDateTime

class UserServiceTest : DescribeSpec( {

    val userTokenServiceMock: UserTokenService = mockk(relaxed = true)
    val userRepositoryMock: UserRepository = mockk(relaxed = true)
    val authServiceMock: AuthService = mockk(relaxed = true)
    val babyServiceMock: BabyService = mockk(relaxed = true)
    val refreshTokenRepositoryMock: RefreshTokenRepository = mockk(relaxed = true)

    val sut = UserService(
        userTokenServiceMock,
        userRepositoryMock,
        authServiceMock,
        babyServiceMock,
        refreshTokenRepositoryMock,
    )

    describe("회원 가입 테스트") {
        val babiesRequests = enumValues<Gender>().map {
            Signup.Request.Baby(
                "baby${it.ordinal}",
                "profileImgUrl",
                it,
                LocalDateTime.now(),
            )
        }

        context("정상적인 회원가입 요청이 들어온 경우") {
            val request = Signup.Request(
                "registerToken",
                "nickname",
                babiesRequests,
            )

            it("엑세스 토큰과 리프레쉬 토큰을 반환한다.") {
                val result = sut.signup(request)

                result.shouldBeTypeOf<Signup.Response>()
                result.accessToken.shouldBeTypeOf<String>()
                result.refreshToken.shouldBeTypeOf<String>()
            }
        }

        context("registerToken이 유효하지 않은 경우") {
            val request = Signup.Request(
                "invalidRegisterToken",
                "nickname",
                babiesRequests,
            )

            every {
                userTokenServiceMock.resolveRegisterToken(request.registerToken)
            } throws BusinessException(JwtError.JWT_EXCEPTION)

            it("토큰 에러가 발생한다.") {
                val result = shouldThrow<BusinessException> { sut.signup(request) }
                result.errorCode shouldBe JwtError.JWT_EXCEPTION
                result.message shouldBe JwtError.JWT_EXCEPTION.message
            }
        }
    }
})
