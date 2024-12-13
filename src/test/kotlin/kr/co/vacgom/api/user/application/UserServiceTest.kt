package kr.co.vacgom.api.user.application

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import io.mockk.every
import io.mockk.mockk
import kr.co.vacgom.api.auth.jwt.exception.JwtError
import kr.co.vacgom.api.auth.oauth.enums.SocialLoginProvider
import kr.co.vacgom.api.baby.application.BabyService
import kr.co.vacgom.api.baby.domain.enums.Gender
import kr.co.vacgom.api.babymanager.application.BabyManagerService
import kr.co.vacgom.api.global.exception.error.BusinessException
import kr.co.vacgom.api.user.domain.User
import kr.co.vacgom.api.user.domain.enums.UserRole
import kr.co.vacgom.api.user.exception.UserError
import kr.co.vacgom.api.user.presentation.dto.Signup
import kr.co.vacgom.api.user.repository.UserRepository
import java.time.LocalDate

class UserServiceTest: DescribeSpec( {
    val userTokenServiceMock: UserTokenService = mockk(relaxed = true)
    val userRepositoryMock: UserRepository = mockk(relaxed = true)
    val authServiceMock: AuthService = mockk(relaxed = true)
    val babyServiceMock: BabyService = mockk(relaxed = true)
    val babyManagerServiceMock: BabyManagerService = mockk(relaxed = true)

    val sut = UserService(
        userTokenServiceMock,
        userRepositoryMock,
        authServiceMock,
        babyServiceMock,
        babyManagerServiceMock,
    )

    describe("회원 상세 조회 테스트") {
        val targetUser = User(
            nickname = "nickname",
            socialId = "socialId",
            provider = SocialLoginProvider.KAKAO,
            role = UserRole.ROLE_USER,
        )

        context("조회하고자 하는 유저가 존재하는 경우") {
            every { userRepositoryMock.findById(targetUser.id) } returns targetUser

            it("해당 유저의 상세 정보를 반환한다.") {
                val result = sut.getUserDetail(targetUser.id)

                result.id shouldBe targetUser.id
                result.nickname shouldBe targetUser.nickname
                result.socialId shouldBe targetUser.socialId
                result.provider shouldBe targetUser.provider
                result.role shouldBe targetUser.role
            }
        }

        context("조회하고자 하는 유저가 존재하지 않는 경우") {
            every { userRepositoryMock.findById(targetUser.id) } throws BusinessException(UserError.USER_NOT_FOUND)
            it("UserNotFound 예외를 발생시킨다.") {
                val result = shouldThrow<BusinessException> { sut.getUserDetail(targetUser.id) }

                result.errorCode shouldBe UserError.USER_NOT_FOUND
                result.message shouldBe UserError.USER_NOT_FOUND.message
            }
        }


    }

    describe("회원 가입 테스트") {
        val babiesRequests = enumValues<Gender>().map {
            Signup.Request.Baby(
                "baby${it.ordinal}",
                "profileImgUrl",
                it,
                LocalDate.now()
            )
        }

        context("정상적인 회원가입 요청이 들어온 경우") {
            val request = Signup.Request(
                "registerToken",
                "nickname",
                babiesRequests,
            )

            it("정상적으로 엑세스 토큰과 리프레쉬 토큰을 반환한다.") {
                val result = sut.signup(request)

                result.shouldBeTypeOf<Signup.Response>()
                result.accessToken.shouldBeTypeOf<String>()
                result.refreshToken.shouldBeTypeOf<String>()
            }
        }

        context("등록하는 아기 정보가 없는 경우") {
            val request = Signup.Request(
                "registerToken",
                "nickname",
                emptyList(),
            )

            it("정상적으로 엑세스 토큰과 리프레쉬 토큰을 반환한다.") {
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
