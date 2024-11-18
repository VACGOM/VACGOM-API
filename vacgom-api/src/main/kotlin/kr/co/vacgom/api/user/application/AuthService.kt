package kr.co.vacgom.api.user.application

import kr.co.vacgom.api.auth.oauth.OAuthHandler
import kr.co.vacgom.api.auth.oauth.enums.SocialLoginProvider
import kr.co.vacgom.api.global.exception.error.BusinessException
import kr.co.vacgom.api.global.security.util.SecurityContextUtil
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
        val findUser = userRepository.findBySocialId(socialAuthInfo.socialId)
            ?: return Login.Response.Register(
                    userTokenService.createRegisterToken(socialAuthInfo.socialId, provider)
                )

        return Login.Response.Success(
            userTokenService.createAccessToken(findUser.id, findUser.roles),
            userTokenService.createRefreshToken(findUser.id, findUser.roles),
        )
    }

    fun logout() {
        val userId = SecurityContextUtil.getPrincipal()
        userTokenService.deleteRefreshToken(userId)
    }

    fun unlinkUser(user: User) {
        val socialId = user.socialId ?: throw BusinessException(UserError.SOCIAL_ID_NOT_FOUND)
        oauthHandler.handleUnlinkUser(user.provider, socialId)
    }
}
