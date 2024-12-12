package kr.co.vacgom.api.redis

import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
private class RedissonConfig(
    @Value("\${redis.host}")
    private val host: String,

    @Value("\${redis.port}")
    private val port: Int,
) {
    @Bean
    fun redissonClient(): RedissonClient {
        val config = Config()
        config.useSingleServer().address = "$host:$port"

        return Redisson.create(config)
    }
}
