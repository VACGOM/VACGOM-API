package kr.co.vacgom.api.global.config

import kr.co.vacgom.api.global.exception.ApiExceptionHandlingFilter
import kr.co.vacgom.api.global.filter.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity,
                            jwtAuthenticationFilter: JwtAuthenticationFilter,
                            apiExceptionHandlingFilter: ApiExceptionHandlingFilter,
    ): SecurityFilterChain {
        httpSecurity {
            authorizeRequests { authorize(anyRequest, permitAll) }
            httpBasic { disable() }
            formLogin { disable() }
            logout { disable() }
            sessionManagement { sessionCreationPolicy = SessionCreationPolicy.STATELESS }
            csrf { disable() }
            addFilterBefore<UsernamePasswordAuthenticationFilter>(jwtAuthenticationFilter)
            addFilterBefore<JwtAuthenticationFilter>(apiExceptionHandlingFilter)
        }
        return httpSecurity.build()
    }
}
