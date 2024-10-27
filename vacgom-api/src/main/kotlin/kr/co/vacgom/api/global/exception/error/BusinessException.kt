package kr.co.vacgom.api.global.exception.error

class BusinessException(
    val errorCode: ErrorCode,
) : RuntimeException(errorCode.message)
