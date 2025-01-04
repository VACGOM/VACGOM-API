package kr.co.vacgom.api.auth.security.config

import kr.co.vacgom.api.auth.filter.JwtAuthenticationFilter
import kr.co.vacgom.api.global.exception.ApiExceptionHandlingFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun securityFilterChain(
        httpSecurity: HttpSecurity,
        jwtAuthenticationFilter: JwtAuthenticationFilter,
        apiExceptionHandlingFilter: ApiExceptionHandlingFilter,
    ): SecurityFilterChain {
        val configuration = CorsConfiguration()
        val source = UrlBasedCorsConfigurationSource()

        configuration.allowedOrigins = listOf(
            "http://localhost:3000",
            "http://localhost:80",
            "https://vacgom.co.kr"
        )
        configuration.allowedMethods = listOf(
            "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"
        )
        configuration.allowedHeaders = listOf("*")

        source.registerCorsConfiguration("/**", configuration)

        httpSecurity {
            cors { configurationSource = source }
            authorizeHttpRequests { authorize(anyRequest, permitAll) }
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

    @Bean
    fun userDetailsService(): UserDetailsService {
        return InMemoryUserDetailsManager()
    }
}
