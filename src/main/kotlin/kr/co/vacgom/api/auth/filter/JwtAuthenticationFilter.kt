package kr.co.vacgom.api.auth.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kr.co.vacgom.api.auth.jwt.JwtProvider
import kr.co.vacgom.api.auth.security.UserAuthentication
import kr.co.vacgom.api.baby.presentation.BabyApi.Companion.BABY
import kr.co.vacgom.api.global.exception.error.BusinessException
import kr.co.vacgom.api.global.exception.error.GlobalError
import kr.co.vacgom.api.global.presentation.GlobalPath.BASE_V3
import kr.co.vacgom.api.invitation.presentation.InvitationApi
import kr.co.vacgom.api.invitation.presentation.InvitationApi.Companion.INVITATION
import kr.co.vacgom.api.user.application.UserTokenService
import kr.co.vacgom.api.user.presentation.AuthApi
import kr.co.vacgom.api.user.presentation.AuthApi.Companion.AUTH
import kr.co.vacgom.api.user.presentation.UserApi
import kr.co.vacgom.api.user.presentation.UserApi.Companion.USER
import org.springframework.http.HttpMethod
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.AntPathMatcher
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val userTokenService: UserTokenService,
    private val jwtProvider: JwtProvider,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        jwtProvider.extractToken(request)?.run {
            val accessToken = userTokenService.resolveAccessToken(this)
            val userAuthentication = UserAuthentication(accessToken.userId, accessToken.authorities)

            SecurityContextHolder.getContext().authentication = userAuthentication
        } ?: throw BusinessException(GlobalError.UNAUTHORIZED)

        filterChain.doFilter(request, response)
        SecurityContextHolder.clearContext()
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return ignoredPath.any { (ignoredPathURI, method) ->
            val matchedPath = AntPathMatcher().matchStart(ignoredPathURI, request.requestURI)
            val matchedMethod = method.matches(request.method)
            matchedPath && matchedMethod
        }
    }

    companion object {
        private val ignoredPath: Map<String, HttpMethod> = mapOf(
            "/actuator/health" to HttpMethod.GET,
            "/swagger-ui/**" to HttpMethod.GET,
            "/v3/api-docs/swagger-config" to HttpMethod.GET,
            "/v3/api-docs.yaml" to HttpMethod.GET,
            BASE_V3 + BABY + "/images" to HttpMethod.POST,
            BASE_V3 + AUTH + "/login/**" to HttpMethod.POST,
            BASE_V3 + USER to HttpMethod.POST,
            BASE_V3 + "/**" to HttpMethod.OPTIONS,
            BASE_V3 + USER + INVITATION to HttpMethod.POST,
            BASE_V3 + INVITATION to HttpMethod.POST,
            BASE_V3 + "/TEST/**" to HttpMethod.POST
        )
    }
}
