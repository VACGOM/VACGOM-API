package kr.co.vacgom.api.vaccination.application.dto

import java.time.LocalDate
import java.util.*

class CreateVaccinations {
    sealed class ClientRequest {

        data class Request(val vaccinations: List<VaccinationRequest>)

        data class VaccinationRequest(
            val doseRound: Long,
            val doseDescription: String?,
            val vaccinatedDate: LocalDate,
            val vaccinationFacility: String?,
            val vaccineManufacturer: String?,
            val vaccineProductName: String?,
            val vaccineLotNumber: String?
        )
    }

    sealed class ClientResponse(val id: UUID)
}
