package kr.co.vacgom.api.global.exception

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kr.co.vacgom.api.global.exception.error.BusinessException
import kr.co.vacgom.api.global.exception.error.ErrorResponse
import kr.co.vacgom.api.global.exception.error.GlobalError
import org.slf4j.Logger
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import java.io.IOException
import java.nio.charset.Charset


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class ApiExceptionHandlingFilter(
    private val objectMapper: ObjectMapper,
    private val log: Logger
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
    ) = try {
        val wrappedRequest = ContentCachingRequestWrapper(request)
        val inputStream = wrappedRequest.inputStream
        inputStream.readBytes()
        val responseWrapper = ContentCachingResponseWrapper(response)
        logRequestBasic(wrappedRequest)
        logRequestBody(wrappedRequest)
        chain.doFilter(wrappedRequest, responseWrapper)

        if (!request.requestURI.contains("/swagger")) {
            logResponse(responseWrapper)
        }
        responseWrapper.copyBodyToResponse()

    } catch (exception: BusinessException) {
        setErrorResponse(response, exception)
    } catch (exception: Exception) {
        setErrorResponse(response, BusinessException(GlobalError.INTERNAL_SERVER_ERROR))
    }

    private fun logRequestBasic(request: ContentCachingRequestWrapper) {
        val method = request.method
        val requestURI = request.requestURI
        val remoteAddr = request.remoteAddr
        val userAgent = request.getHeader("User-Agent")

        log.info("Request: $method $requestURI | $remoteAddr")
        log.info("Request Agent: $userAgent")

        val parameterMap = request.parameterMap.map { (key, value) ->
            "$key=${value.joinToString(", ")}"
        }.joinToString(" ")
        if (parameterMap.isNotEmpty()) {
            log.info("Request Parameters: $parameterMap")
        }

    }

    private fun logRequestBody(request: ContentCachingRequestWrapper) {
        val content = request.contentAsByteArray
        if (content.isNotEmpty()) {
            try {
                val contentString = String(content, Charset.defaultCharset())
                val jsonNode = objectMapper.readTree(contentString)
                log.info("Request Body: ${objectMapper.writeValueAsString(jsonNode)}")
            } catch (e: Exception) {
                val contentString = String(content, Charset.defaultCharset())
                log.info("Request Body: $contentString")
            }
        }
    }

    private fun logResponse(response: ContentCachingResponseWrapper) {
        val status = response.status
        val responseBody = String(response.contentAsByteArray, Charset.defaultCharset())

        log.info("Response: $status")
        if (responseBody.isNotEmpty()) {
            try {
                val jsonNode = objectMapper.readTree(responseBody)
                log.info("Response Body: ${objectMapper.writeValueAsString(jsonNode)}")
            } catch (e: Exception) {
                log.info("Response Body: $responseBody")
            }
        }
    }

    private fun setErrorResponse(
        response: HttpServletResponse,
        exception: BusinessException,
    ) = try {
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.status = exception.errorCode.status.value()

        val errorResponse = ErrorResponse(exception.errorCode)
        objectMapper.writeValue(response.outputStream, errorResponse)

        log.info("Response Status: ${response.status}")
        log.info("Response Body: ${objectMapper.writeValueAsString(errorResponse)}")

    } catch (exception: IOException) {
        throw RuntimeException(exception)
    }
}
