package kr.co.vacgom.api.user.application

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import io.mockk.every
import io.mockk.mockk
import kr.co.vacgom.api.auth.jwt.exception.JwtError
import kr.co.vacgom.api.baby.application.BabyCommandService
import kr.co.vacgom.api.baby.application.BabyQueryService
import kr.co.vacgom.api.baby.domain.enums.Gender
import kr.co.vacgom.api.babymanager.application.BabyManagerService
import kr.co.vacgom.api.global.exception.error.BusinessException
import kr.co.vacgom.api.user.presentation.dto.SignupDto
import kr.co.vacgom.api.user.repository.UserRepository
import java.time.LocalDate

class UserCommandServiceTest : DescribeSpec({
    val userTokenServiceMock: UserTokenService = mockk(relaxed = true)
    val userRepositoryMock: UserRepository = mockk(relaxed = true)
    val authServiceMock: AuthService = mockk(relaxed = true)
    val babyCommandServiceMock: BabyCommandService = mockk(relaxed = true)
    val babyManagerServiceMock: BabyManagerService = mockk(relaxed = true)
    val babyQueryServiceMock: BabyQueryService = mockk(relaxed = true)

    val sut = UserCommandService(
        userTokenServiceMock,
        userRepositoryMock,
        authServiceMock,
        babyCommandServiceMock,
        babyQueryServiceMock,
        babyManagerServiceMock,
    )

    describe("회원 가입 테스트") {
        val babiesRequests = enumValues<Gender>().map {
            SignupDto.Request.Baby(
                "baby${it.ordinal}",
                it,
                "profileImgUrl",
                LocalDate.now(),
            )
        }

        context("정상적인 회원가입 요청이 들어온 경우") {
            val request = SignupDto.Request(
                "registerToken",
                "nickname",
                babiesRequests,
            )

            it("정상적으로 엑세스 토큰과 리프레쉬 토큰을 반환한다.") {
                val result = sut.signup(request)

                result.shouldBeTypeOf<SignupDto.Response>()
                result.accessToken.shouldBeTypeOf<String>()
                result.refreshToken.shouldBeTypeOf<String>()
            }
        }

        context("등록하는 아기 정보가 없는 경우") {
            val request = SignupDto.Request(
                "registerToken",
                "nickname",
                emptyList(),
            )

            it("정상적으로 엑세스 토큰과 리프레쉬 토큰을 반환한다.") {
                val result = sut.signup(request)

                result.shouldBeTypeOf<SignupDto.Response>()
                result.accessToken.shouldBeTypeOf<String>()
                result.refreshToken.shouldBeTypeOf<String>()
            }
        }

        context("registerToken이 유효하지 않은 경우") {
            val request = SignupDto.Request(
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
