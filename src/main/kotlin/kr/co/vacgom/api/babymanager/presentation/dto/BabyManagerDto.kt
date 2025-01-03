package kr.co.vacgom.api.babymanager.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

class BabyManagerDto {
    class Request {
        @Schema(name = "BabyManagerDto.Request.Delete")
        data class Delete(
            val babyId: UUID,
            val managerId: UUID
        )

        @Schema(name = "BabyManagerDto.Request.Unlink")
        data class Unlink(
            val babyId: UUID,
        )
    }
}
