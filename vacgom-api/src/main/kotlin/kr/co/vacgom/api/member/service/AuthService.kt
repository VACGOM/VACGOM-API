package kr.co.vacgom.api.member.service

import kr.co.vacgom.api.auth.client.OAuthHandler
import kr.co.vacgom.api.auth.client.enums.SocialLoginProvider
import kr.co.vacgom.api.global.exception.error.BusinessException
import kr.co.vacgom.api.global.security.SecurityContextHolder
import kr.co.vacgom.api.member.exception.MemberError
import kr.co.vacgom.api.member.presentation.dto.Login
import kr.co.vacgom.api.member.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val oauthHandler: OAuthHandler,
    private val memberTokenService: MemberTokenService,
    private val memberRepository: MemberRepository,
) {
    fun socialLogin(provider: String, request: Login.Request.Social): Login.Response {
        val authInfo = oauthHandler.handleUserInfo(SocialLoginProvider.parse(provider), request)
        val findMember = memberRepository.findBySocialId(authInfo.socialId)
            ?: throw BusinessException(MemberError.MEMBER_NOT_FOUND)

        return Login.Response(
            memberTokenService.createAccessToken(findMember.userId),
            memberTokenService.createRefreshToken(findMember.userId),
        )
    }

    fun localLogin(request: Login.Request.Local): Login.Response {
        val findMember = memberRepository.findByIdAndPassword(request.id, request.password)
            ?: throw BusinessException(MemberError.MEMBER_NOT_FOUND)

        return Login.Response(
            memberTokenService.createAccessToken(findMember.userId),
            memberTokenService.createRefreshToken(findMember.userId),
        )
    }

    fun logout() {
        val authentication = SecurityContextHolder.getAuthentication()
        memberTokenService.deleteRefreshToken(authentication.userId)
    }

    fun revoke() {
        val authentication = SecurityContextHolder.getAuthentication()
        val findMember = memberRepository.findByUserId(authentication.userId)
            ?: throw BusinessException(MemberError.MEMBER_NOT_FOUND)

        oauthHandler.handleRevokeUser(findMember.provider, findMember.socialId)
    }
}
