package kr.co.vacgom.api.member.service

import kr.co.vacgom.api.global.exception.error.BusinessException
import kr.co.vacgom.api.member.exception.MemberError
import kr.co.vacgom.api.member.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val authService: AuthService,
) {
    fun signup() {
        // TODO(멤버 저장 & 토큰 리턴 구현 필요)
    }

    fun revoke(userId: Long) {
        val findMember = memberRepository.findByUserId(userId)
            ?: throw BusinessException(MemberError.MEMBER_NOT_FOUND)

        authService.unlinkUser(findMember)
        memberRepository.deleteByUserId(userId)
    }
}
