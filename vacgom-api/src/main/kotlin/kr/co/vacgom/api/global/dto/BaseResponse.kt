package kr.co.vacgom.api.global.dto

data class BaseResponse<T>(
        val result: Boolean,
        val message: String?,
        val data: T?,
) {
    companion object {
        fun <T> success(data: T?): BaseResponse<T> {
            return BaseResponse(true, null, data)
        }

        fun <T> fail(message: String): BaseResponse<T> {
            return BaseResponse(false, message, null)
        }

        fun <T> fail(data: T?): BaseResponse<T> {
            return BaseResponse(false, null, data)
        }
    }
}
