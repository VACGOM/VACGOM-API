package kr.co.vacgom.api.global.exception

import kr.co.vacgom.api.global.exception.error.BusinessException
import kr.co.vacgom.api.global.exception.error.ErrorCode
import kr.co.vacgom.api.global.exception.error.ErrorResponse
import kr.co.vacgom.api.global.exception.error.GlobalError
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.resource.NoResourceFoundException

@Slf4j
@RestControllerAdvice
class ApiExceptionHandler(
    private val log: Logger
) {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(exception: MethodArgumentNotValidException): ErrorResponse {
        log.warn(exception.message)
        return ErrorResponse(exception.fieldErrors)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun missingServletRequestParameterException(exception: MissingServletRequestParameterException): ErrorResponse {
        log.warn(exception.message)
        return ErrorResponse(GlobalError.INVALID_REQUEST_PARAM)
    }

    @ExceptionHandler(NoResourceFoundException::class)
    fun handleUnexpectedException(exception: NoResourceFoundException): ErrorResponse {
        log.warn(exception.message)
        return ErrorResponse(GlobalError.GLOBAL_NOT_FOUND);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(exception: BusinessException): ResponseEntity<ErrorResponse?> {
        log.warn(exception.message)
        return convert(exception.errorCode)
    }

    private fun convert(errorCode: ErrorCode): ResponseEntity<ErrorResponse?> {
        return ResponseEntity
            .status(errorCode.status)
            .body(ErrorResponse(errorCode))
    }
}
