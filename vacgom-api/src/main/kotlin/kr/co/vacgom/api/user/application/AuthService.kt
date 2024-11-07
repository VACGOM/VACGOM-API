package kr.co.vacgom.api.user.application

import kr.co.vacgom.api.auth.client.OAuthHandler
import kr.co.vacgom.api.auth.client.enums.SocialLoginProvider
import kr.co.vacgom.api.global.exception.error.BusinessException
import kr.co.vacgom.api.global.security.SecurityContextHolder
import kr.co.vacgom.api.user.domain.User
import kr.co.vacgom.api.user.exception.UserError
import kr.co.vacgom.api.user.presentation.dto.Login
import kr.co.vacgom.api.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val oauthHandler: OAuthHandler,
    private val userTokenService: UserTokenService,
    private val userRepository: UserRepository,
) {
    fun socialLogin(provider: String, request: Login.Request.Social): Login.Response {
        val socialAuthInfo = oauthHandler.handleUserInfo(SocialLoginProvider.parse(provider), request)
        val findMember = userRepository.findBySocialId(socialAuthInfo.socialId)
            ?: return Login.Response.Register(
                    userTokenService.createRegisterToken(socialAuthInfo.socialId)
                )

        return Login.Response.Success(
            userTokenService.createAccessToken(findMember.userId),
            userTokenService.createRefreshToken(findMember.userId),
        )
    }

    fun localLogin(request: Login.Request.Local): Login.Response.Success {
        val findMember = userRepository.findByIdAndPassword(request.id, request.password)
            ?: throw BusinessException(UserError.USER_NOT_FOUND)
        // Todo("RegisterToken 발급 여부 확정에 따라 로직 변경 필요")

        return Login.Response.Success(
            userTokenService.createAccessToken(findMember.userId),
            userTokenService.createRefreshToken(findMember.userId),
        )
    }

    fun logout() {
        val authentication = SecurityContextHolder.getAuthentication()
        userTokenService.deleteRefreshToken(authentication.userId)
    }

    fun unlinkUser(user: User) {
        val socialId = user.socialId ?: throw BusinessException(UserError.SOCIAL_ID_NOT_FOUND)
        oauthHandler.handleUnlinkUser(user.provider, socialId)
    }
}
