package kr.co.vacgom.api.auth.oauth.config

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients(basePackages = ["kr.co.vacgom.api.auth.oauth"])
class FeignClientConfig
