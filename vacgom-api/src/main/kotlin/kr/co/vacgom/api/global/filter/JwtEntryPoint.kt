package kr.co.vacgom.api.global.filter

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kr.co.vacgom.api.global.exception.error.BusinessException
import kr.co.vacgom.api.global.exception.error.ErrorResponse
import kr.co.vacgom.api.global.exception.error.GlobalError
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtEntryPoint : OncePerRequestFilter() {

    private val objectMapper = ObjectMapper()

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        try {
            filterChain.doFilter(request, response)
        } catch (exception: BusinessException) {
            logger.info("jwt 에러 발생")
            handleException(response, exception)
        }
    }

    private fun handleException(response: HttpServletResponse, exception: BusinessException) {
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        response.contentType = "application/json"
        response.characterEncoding = "UTF-8"
        response.writer.write(objectMapper.writeValueAsString(ErrorResponse(GlobalError.INTERNAL_SERVER_ERROR, exception.message)))
    }
}
