package kr.co.vacgom.api.user.application

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kr.co.vacgom.api.auth.oauth.enums.SocialLoginProvider
import kr.co.vacgom.api.global.exception.error.BusinessException
import kr.co.vacgom.api.user.domain.User
import kr.co.vacgom.api.user.domain.enums.UserRole
import kr.co.vacgom.api.user.exception.UserError
import kr.co.vacgom.api.user.repository.UserRepository

class UserQueryServiceTest : DescribeSpec({
    val userRepositoryMock: UserRepository = mockk(relaxed = true)

    val sut = UserQueryService(
        userRepositoryMock,
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
})
