package kr.co.vacgom.api.vaccine.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate
import java.util.*

class VaccinationDto {
    class Request {
        @Schema(name = "VaccinationDto.Request.Create")
        data class Create(
            val babyId: UUID,
            val vaccinationRequests: List<VaccinationRequest>
        )

        data class VaccinationRequest(
            val doseRound: Long?,
            val doseRoundDescription: String?,
            val facility: String?,
            val lotNumber: String?,
            val manufacturer: String?,
            val productName: String?,
            val name: String,
            val vaccinatedAt: LocalDate
        )
    }
}
