package kr.co.vacgom.api.global.security.util

import org.springframework.security.core.context.SecurityContextHolder

object SecurityContextUtil {
    fun getPrincipal(): Long {
        return SecurityContextHolder.getContext().authentication.principal as Long
    }
}
