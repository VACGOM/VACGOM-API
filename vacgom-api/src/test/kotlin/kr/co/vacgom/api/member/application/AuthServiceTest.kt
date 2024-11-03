package kr.co.vacgom.api.member.application

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import io.mockk.every
import io.mockk.mockk
import kr.co.vacgom.api.auth.client.OAuthHandler
import kr.co.vacgom.api.auth.client.enums.SocialLoginProvider
import kr.co.vacgom.api.global.exception.error.BusinessException
import kr.co.vacgom.api.member.domain.Member
import kr.co.vacgom.api.member.exception.MemberError
import kr.co.vacgom.api.member.presentation.dto.Login
import kr.co.vacgom.api.member.repository.MemberRepository

class AuthServiceTest : DescribeSpec({

    val oauthHandlerMock: OAuthHandler = mockk(relaxed = true)
    val memberTokenServiceMock: MemberTokenService = mockk(relaxed = true)
    val memberRepositoryMock: MemberRepository = mockk(relaxed = true)

    val sut = AuthService(
        oauthHandlerMock,
        memberTokenServiceMock,
        memberRepositoryMock
    )

    describe("소셜 로그인 테스트") {
        enumValues<SocialLoginProvider>().forEach { provider ->
            context("$provider 소셜 로그인 시도 과정에서 회원이 존재한다면") {
                it("엑세스 토큰과 리프레쉬 토큰을 반환한다.") {
                    val request = Login.Request.Social("oauth_access_token")

                    every { memberRepositoryMock.findBySocialId(any()) } returns savedMember

                    val result = sut.socialLogin(provider.name, request)

                    result.shouldBeTypeOf<Login.Response.Success>()
                    result.accessToken.shouldBeTypeOf<String>()
                    result.refreshToken.shouldBeTypeOf<String>()
                }
            }
        }

        enumValues<SocialLoginProvider>().forEach { provider ->
            context("$provider 소셜 로그인 시도 과정에서 회원이 존재하지 않는다면") {
                it("RegisterToken을 반환한다.") {
                    val request = Login.Request.Social("oauth_access_token")

                    every { memberRepositoryMock.findBySocialId(any()) } returns null

                    val result = sut.socialLogin(provider.name, request)

                    result.shouldBeTypeOf<Login.Response.Register>()
                    result.registerToken.shouldBeTypeOf<String>()
                }
            }
        }
    }

    describe("자체 로그인 테스트") {
        context("자체 로그인 시도 과정에서 회원이 존재한다면") {
            it("엑세스 토큰과 리프레쉬 토큰을 반환한다.") {
                val request = Login.Request.Local("id", "password")

                every { memberRepositoryMock.findByIdAndPassword(savedMember.id, savedMember.password) } returns savedMember

                val result = sut.localLogin(request)

                result.shouldBeTypeOf<Login.Response.Success>()
                result.accessToken.shouldBeTypeOf<String>()
                result.refreshToken.shouldBeTypeOf<String>()
            }
        }

        context("자체 로그인 시도 과정에서 회원이 존재하지 않는다면") {
            it("MEMBER_NOT_FOUND 예외를 발생시킨다.") {
                val request = Login.Request.Local("id", "password")

                every { memberRepositoryMock.findByIdAndPassword(savedMember.id, savedMember.password) } returns null

                val exception = shouldThrow<BusinessException> {
                    sut.localLogin(request)
                }

                exception.errorCode shouldBe MemberError.MEMBER_NOT_FOUND
            }
        }
    }
}) {
    companion object {
        val savedMember = Member(1L, null, SocialLoginProvider.KAKAO, "name", "id", "password")
    }
}
