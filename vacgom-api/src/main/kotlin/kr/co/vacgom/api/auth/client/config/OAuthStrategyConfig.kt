package kr.co.vacgom.api.auth.client.config

import kr.co.vacgom.api.auth.client.OAuthStrategy
import kr.co.vacgom.api.auth.client.enums.SocialLoginProvider
import kr.co.vacgom.api.auth.client.kakao.KakaoOAuthStrategy
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
