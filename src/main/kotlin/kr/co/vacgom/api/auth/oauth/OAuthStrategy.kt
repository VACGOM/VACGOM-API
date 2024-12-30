package kr.co.vacgom.api.auth.oauth

import kr.co.vacgom.api.auth.oauth.dto.SocialAuthInfo
import kr.co.vacgom.api.user.presentation.dto.LoginDto

interface OAuthStrategy {
    fun getUserInfo(request: LoginDto.Request.Social): SocialAuthInfo
    fun revokeUser(socialId: String)
}
