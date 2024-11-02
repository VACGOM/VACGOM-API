package kr.co.vacgom.api.global.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kr.co.vacgom.api.global.security.SecurityContext
import kr.co.vacgom.api.global.security.SecurityContextHolder
import kr.co.vacgom.api.global.security.UserAuthentication
import kr.co.vacgom.api.member.service.MemberTokenService
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val memberTokenService: MemberTokenService,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        memberTokenService.extractToken(request)?.run {
            val accessUserId = memberTokenService.resolveToken(this)
            val userAuthentication = UserAuthentication(accessUserId)
            SecurityContextHolder.setContext(SecurityContext(userAuthentication))
        }

        // TODO("extractToken 값 null일 때 처리 핊요")

        filterChain.doFilter(request, response)
        SecurityContextHolder.clearContext()
    }
}
