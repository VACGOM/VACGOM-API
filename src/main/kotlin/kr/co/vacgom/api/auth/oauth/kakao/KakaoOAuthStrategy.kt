package kr.co.vacgom.api.auth.oauth.kakao

import kr.co.vacgom.api.auth.oauth.OAuthStrategy
import kr.co.vacgom.api.auth.oauth.dto.SocialAuthInfo
import kr.co.vacgom.api.user.presentation.dto.LoginDto
import org.springframework.stereotype.Component

@Component
class KakaoOAuthStrategy(
    private val kakaoFeignClient: KakaoFeignClient,
    private val kakaoProperties: KakaoProperties,
): OAuthStrategy {

    override fun getUserInfo(request: LoginDto.Request.Social): SocialAuthInfo {
        val kakaoUserInfo = kakaoFeignClient.getUserInfo(BEARER_PREFIX + request.accessToken)
        return SocialAuthInfo(kakaoUserInfo.id)
    }

    override fun revokeUser(socialId: String) {
        kakaoFeignClient.revokeUser(ADMIN_KEY_PREFIX + kakaoProperties.adminKey, socialId)
    }

    companion object {
        const val BEARER_PREFIX = "Bearer "
        const val ADMIN_KEY_PREFIX = "KakaoAK "
    }
}
