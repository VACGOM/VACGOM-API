package kr.co.vacgom.api.member.application

import kr.co.vacgom.api.auth.client.OAuthHandler
import kr.co.vacgom.api.auth.client.enums.SocialLoginProvider
import kr.co.vacgom.api.global.exception.error.BusinessException
import kr.co.vacgom.api.global.security.SecurityContextHolder
import kr.co.vacgom.api.member.domain.Member
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
        val socialAuthInfo = oauthHandler.handleUserInfo(SocialLoginProvider.parse(provider), request)
        val findMember = memberRepository.findBySocialId(socialAuthInfo.socialId)
            ?: return Login.Response.Register(
                    memberTokenService.createRegisterToken(socialAuthInfo.socialId)
                )

        return Login.Response.Success(
            memberTokenService.createAccessToken(findMember.userId),
            memberTokenService.createRefreshToken(findMember.userId),
        )
    }

    fun localLogin(request: Login.Request.Local): Login.Response.Success {
        val findMember = memberRepository.findByIdAndPassword(request.id, request.password)
            ?: throw BusinessException(MemberError.MEMBER_NOT_FOUND)
        // Todo("RegisterToken 발급 여부 확정에 따라 로직 변경 필요")

        return Login.Response.Success(
            memberTokenService.createAccessToken(findMember.userId),
            memberTokenService.createRefreshToken(findMember.userId),
        )
    }

    fun logout() {
        val authentication = SecurityContextHolder.getAuthentication()
        memberTokenService.deleteRefreshToken(authentication.userId)
    }

    fun unlinkUser(member: Member) {
        val socialId = member.socialId ?: throw BusinessException(MemberError.SOCIAL_ID_NOT_FOUND)
        oauthHandler.handleUnlinkUser(member.provider, socialId)
    }
}
