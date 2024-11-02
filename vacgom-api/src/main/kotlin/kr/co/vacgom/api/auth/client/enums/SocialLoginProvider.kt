package kr.co.vacgom.api.auth.client.enums

enum class SocialLoginProvider{
    KAKAO,
    ;

    companion object {
        fun parse(provider: String): SocialLoginProvider = SocialLoginProvider.valueOf(provider.uppercase())
    }
}
