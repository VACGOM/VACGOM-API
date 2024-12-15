package kr.co.vacgom.api.invitation.repository

import com.fasterxml.jackson.databind.ObjectMapper
import kr.co.vacgom.api.invitation.domain.InvitationCode
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class InvitationRedisRepository(
    private val redisTemplate: RedisTemplate<String, Any>,
    private val objectMapper: ObjectMapper
) {
    fun save(invitationCode: InvitationCode, ttl: Long) {
        val key = INVITATION_CODE_KEY_PREFIX + invitationCode.key

        objectMapper.writeValueAsString(invitationCode)
            .also { value -> redisTemplate.opsForValue().set(key, value, ttl, TimeUnit.SECONDS)}
    }

    fun getAndDeleteInvitationCode(code: String): InvitationCode? {
        val queryKey = INVITATION_CODE_KEY_PREFIX + code
        val value = redisTemplate.opsForValue().getAndDelete(queryKey)

        return objectMapper.readValue(value.toString(), InvitationCode::class.java)
    }

    companion object{
        const val INVITATION_CODE_KEY_PREFIX = "invitation_code:"
    }
}
