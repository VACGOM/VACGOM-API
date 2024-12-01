package kr.co.vacgom.api.user.application

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import io.mockk.every
import io.mockk.mockk
import kr.co.vacgom.api.auth.oauth.OAuthHandler
import kr.co.vacgom.api.auth.oauth.enums.SocialLoginProvider
import kr.co.vacgom.api.global.exception.error.BusinessException
import kr.co.vacgom.api.user.domain.User
import kr.co.vacgom.api.user.exception.UserError
import kr.co.vacgom.api.user.presentation.dto.Login
import kr.co.vacgom.api.user.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority

class AuthServiceTest : DescribeSpec({

    val oauthHandlerMock: OAuthHandler = mockk(relaxed = true)
    val userTokenServiceMock: UserTokenService = mockk(relaxed = true)
    val userRepositoryMock: UserRepository = mockk(relaxed = true)

    val sut = AuthService(
        oauthHandlerMock,
        userTokenServiceMock,
        userRepositoryMock
    )

    describe("소셜 로그인 테스트") {
        enumValues<SocialLoginProvider>().forEach { provider ->
            context("$provider 소셜 로그인 시도 과정에서 회원이 존재한다면") {
                it("엑세스 토큰과 리프레쉬 토큰을 반환한다.") {
                    val request = Login.Request.Social("oauth_access_token")

                    every { userRepositoryMock.findBySocialId(any()) } returns savedUser

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

                    every { userRepositoryMock.findBySocialId(any()) } returns null

                    val result = sut.socialLogin(provider.name, request)

                    result.shouldBeTypeOf<Login.Response.Register>()
                    result.registerToken.shouldBeTypeOf<String>()
                }
            }
        }
    }

    describe("소셜 로그인 연결 해제 예외 테스트") {
        context("연결 해제 하려는 유저의 socialId가 존재하지 않으면") {
            it("${UserError.SOCIAL_ID_NOT_FOUND.name} 예외가 발생한다.") {
                val notSocialIdUser = User.create(
                    "nickname",
                    null,
                    SocialLoginProvider.KAKAO,
                    emptyList()
                )

                val result = shouldThrow<BusinessException> { sut.unlinkUser(notSocialIdUser) }
                result.errorCode shouldBe UserError.SOCIAL_ID_NOT_FOUND
                result.message shouldBe UserError.SOCIAL_ID_NOT_FOUND.message
            }
        }
    }
}) {
    companion object {
        val savedUser = User.create(
            "nickname",
            "socialId",
            SocialLoginProvider.KAKAO,
            arrayListOf(SimpleGrantedAuthority("ROLE_USER")
        ))
    }
}