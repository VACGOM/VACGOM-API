package kr.co.vacgom.api.auth.client

import kr.co.vacgom.api.auth.client.dto.AuthInfo
import kr.co.vacgom.api.auth.client.enums.SocialLoginProvider
import kr.co.vacgom.api.global.exception.error.BusinessException
import kr.co.vacgom.api.global.exception.error.GlobalError
import kr.co.vacgom.api.member.presentation.dto.Login
import org.springframework.stereotype.Component

@Component
class OAuthHandler(
    private val handlers: Map<SocialLoginProvider, OAuthStrategy>
) {
    fun handleUserInfo(provider: SocialLoginProvider, request: Login.Request.Social): AuthInfo {
        return handlers[provider]?.getUserInfo(request) ?: throw BusinessException(GlobalError.INVALID_REQUEST_PARAM)
    }

    fun handleRevokeUser(provider: SocialLoginProvider, socialId: String) {
        handlers[provider]?.revokeUser(socialId) ?: throw BusinessException(GlobalError.INVALID_REQUEST_PARAM)
    }
}
