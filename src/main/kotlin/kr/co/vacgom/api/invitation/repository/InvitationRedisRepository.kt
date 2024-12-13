package kr.co.vacgom.api.invitation.repository

import kr.co.vacgom.api.invitation.domain.InvitationCode
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
interface InvitationRedisRepository: CrudRepository<InvitationCode, UUID>
