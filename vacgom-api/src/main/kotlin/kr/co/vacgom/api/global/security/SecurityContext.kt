package kr.co.vacgom.api.global.security

class SecurityContext<T: Authentication>(
    private val authentication: T
) {
    fun getAuthentication(): T {
        return authentication
    }
}
