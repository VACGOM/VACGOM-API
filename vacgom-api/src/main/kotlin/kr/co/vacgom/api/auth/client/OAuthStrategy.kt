package kr.co.vacgom.api.auth.client

import kr.co.vacgom.api.auth.client.dto.SocialAuthInfo
import kr.co.vacgom.api.member.presentation.dto.Login

interface OAuthStrategy {
    fun getUserInfo(request: Login.Request.Social): SocialAuthInfo
    fun revokeUser(socialId: String)
}
