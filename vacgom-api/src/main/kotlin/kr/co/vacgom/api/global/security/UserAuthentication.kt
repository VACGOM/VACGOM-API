package kr.co.vacgom.api.global.security

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class UserAuthentication(
    private val userId: Long,
    authorities: Collection<GrantedAuthority>,
) : AbstractAuthenticationToken(authorities) {

    init {
        isAuthenticated = true
    }

    override fun getCredentials(): Any? {
        return null
    }

    override fun getPrincipal(): Any {
        return userId
    }
}
