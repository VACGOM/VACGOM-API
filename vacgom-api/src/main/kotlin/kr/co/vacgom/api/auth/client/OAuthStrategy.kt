package kr.co.vacgom.api.auth.client

import kr.co.vacgom.api.auth.client.dto.AuthInfo
import kr.co.vacgom.api.member.presentation.dto.Login

interface OAuthStrategy {
    fun getUserInfo(request: Login.Request.Social): AuthInfo
    fun revokeUser(socialId: String)
}
