package kr.co.vacgom.api.auth.oauth.config

import kr.co.vacgom.api.auth.oauth.OAuthStrategy
import kr.co.vacgom.api.auth.oauth.enums.SocialLoginProvider
import kr.co.vacgom.api.auth.oauth.kakao.KakaoOAuthStrategy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OAuthStrategyConfig(
    private val kakaoOAuthStrategy: KakaoOAuthStrategy,
) {
    @Bean
    fun oauthHandler(): Map<SocialLoginProvider, OAuthStrategy> {
        return mapOf(
            SocialLoginProvider.KAKAO to kakaoOAuthStrategy,
        )
    }
}
