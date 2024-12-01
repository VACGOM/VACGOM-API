package kr.co.vacgom.api.global.exception

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kr.co.vacgom.api.global.exception.error.BusinessException
import kr.co.vacgom.api.global.exception.error.ErrorResponse
import kr.co.vacgom.api.global.exception.error.GlobalError
import org.slf4j.Logger
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

@Component
class ApiExceptionHandlingFilter(
    private val objectMapper: ObjectMapper,
    private val log: Logger
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
    ) = try {
        chain.doFilter(request, response)
    } catch (exception: BusinessException) {
        log.warn(exception.message)
        setErrorResponse(response, exception)
    } catch (exception: Exception) {
        log.warn(exception.message)
        setErrorResponse(response, BusinessException(GlobalError.INTERNAL_SERVER_ERROR))
    }

    private fun setErrorResponse(
        response: HttpServletResponse,
        exception: BusinessException,
    ) = try {
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.status = exception.errorCode.status.value()

        objectMapper.writeValue(response.outputStream, ErrorResponse(exception.errorCode))
    } catch (exception: IOException) {
        throw RuntimeException(exception)
    }
}
