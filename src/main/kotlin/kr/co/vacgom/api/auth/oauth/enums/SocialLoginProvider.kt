package kr.co.vacgom.api.auth.oauth.enums

enum class SocialLoginProvider{
    KAKAO,
    ;

    companion object {
        fun parse(provider: String): SocialLoginProvider = SocialLoginProvider.valueOf(provider.uppercase())
    }
}
