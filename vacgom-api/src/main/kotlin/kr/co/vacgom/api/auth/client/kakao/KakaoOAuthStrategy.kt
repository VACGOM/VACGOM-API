package kr.co.vacgom.api.auth.client.kakao

import kr.co.vacgom.api.auth.client.OAuthStrategy
import kr.co.vacgom.api.auth.client.dto.AuthInfo
import kr.co.vacgom.api.member.presentation.dto.Login
import org.springframework.stereotype.Component

@Component
class KakaoOAuthStrategy(
    private val kakaoFeignClient: KakaoFeignClient,
    private val kakaoProperties: KakaoProperties,
): OAuthStrategy {

    override fun getUserInfo(request: Login.Request.Social): AuthInfo {
        val kakaoUserInfo = kakaoFeignClient.getUserInfo(BEARER_PREFIX + request.accessToken)
        return AuthInfo(kakaoUserInfo.id)
    }

    override fun revokeUser(socialId: String) {
        kakaoFeignClient.revokeUser(ADMIN_KEY_PREFIX + kakaoProperties.adminKey, socialId)
    }

    companion object {
        const val BEARER_PREFIX = "Bearer "
        const val ADMIN_KEY_PREFIX = "KakaoAK "
    }
}
