package kr.co.vacgom.api.inoculation.application.dto

import java.time.LocalDate
import java.util.*

class CreateInoculations {
    sealed class ClientRequest {

//        data class Request()

        data class InoculationRequest(
            val inoculatedDate: LocalDate,
            val inoculationAgency: String,
            val doseRound: Long,
            val doseDescription: Long,
            val vaccineManufacturer: String?,
            val vaccineProductName: String?
        )
    }

    sealed class ClientResponse(val id: UUID)
}
