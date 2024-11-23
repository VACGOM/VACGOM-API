package kr.co.vacgom.api.auth.security.util

import org.springframework.security.core.context.SecurityContextHolder
import java.util.*

object SecurityContextUtil {
    fun getPrincipal(): UUID {
        return SecurityContextHolder.getContext().authentication.principal as UUID
    }
}
