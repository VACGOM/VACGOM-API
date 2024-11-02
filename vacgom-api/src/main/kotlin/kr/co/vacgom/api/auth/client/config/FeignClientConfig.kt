package kr.co.vacgom.api.auth.client.config

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients(basePackages = ["kr.co.vacgom.api.auth.client"])
class FeignClientConfig
