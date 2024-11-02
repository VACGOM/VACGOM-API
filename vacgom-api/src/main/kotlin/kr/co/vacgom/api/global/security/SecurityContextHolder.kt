package kr.co.vacgom.api.global.security

object SecurityContextHolder {

    private val contextHolder = ThreadLocal<SecurityContext<*>>()

    fun getContext(): SecurityContext<*>? {
        return contextHolder.get()
    }

    fun setContext(context: SecurityContext<*>) {
        contextHolder.set(context)
    }

    fun clearContext() {
        contextHolder.remove()
    }

    fun getAuthentication(): Authentication {
        return contextHolder.get().getAuthentication()
    }
}
