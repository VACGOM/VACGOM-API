package kr.co.vacgom.api.babymanager.presentation.dto

import java.util.*

class BabyManagerDto {
    class Request {
        data class Delete(
            val babyId: UUID,
            val managerId: UUID
        )
    }
}
