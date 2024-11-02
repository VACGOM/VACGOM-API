package kr.co.vacgom.api.auth.client.kakao

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "kakaoFeignClient", url = "https://kapi.kakao.com")
interface KakaoFeignClient {

    @GetMapping("/v2/user/me")
    fun getUserInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) accessToken: String): KakaoUserInfo

    @PostMapping("/v1/user/unlink")
    fun revokeUser(@RequestHeader(HttpHeaders.AUTHORIZATION) adminKey: String,
                   @RequestParam(name = "target_id") targetId: String,
                   @RequestParam(name = "target_id_type") targetIdType: String = "user_id",
    )

    data class KakaoUserInfo(
        val id: String,
    )
}
