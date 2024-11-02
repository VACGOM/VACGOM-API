package kr.co.vacgom.api.auth.client.kakao

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "kakao")
class KakaoProperties(
    val adminKey: String,
)
