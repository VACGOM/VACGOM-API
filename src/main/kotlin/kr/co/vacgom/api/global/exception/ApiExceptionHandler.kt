package kr.co.vacgom.api.global.exception

import kr.co.vacgom.api.global.exception.error.BusinessException
import kr.co.vacgom.api.global.exception.error.ErrorCode
import kr.co.vacgom.api.global.exception.error.ErrorResponse
import kr.co.vacgom.api.global.exception.error.GlobalError
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.resource.NoResourceFoundException

@RestControllerAdvice
class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ErrorResponse {
        return ErrorResponse(ex.fieldErrors)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun missingServletRequestParameterException(): ErrorResponse {
        return ErrorResponse(GlobalError.INVALID_REQUEST_PARAM)
    }

    @ExceptionHandler(NoResourceFoundException::class)
    fun handleUnexpectedException(exception: NoResourceFoundException): ErrorResponse {
        return ErrorResponse(GlobalError.GLOBAL_NOT_FOUND);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(exception: BusinessException): ResponseEntity<ErrorResponse?> {
        return convert(exception.errorCode)
    }

    private fun convert(errorCode: ErrorCode): ResponseEntity<ErrorResponse?> {
        return ResponseEntity
            .status(errorCode.status)
            .body(ErrorResponse(errorCode))
    }
}
