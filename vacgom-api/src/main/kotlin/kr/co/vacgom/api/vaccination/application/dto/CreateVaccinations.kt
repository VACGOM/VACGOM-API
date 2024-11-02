package kr.co.vacgom.api.vaccination.application.dto

import java.time.LocalDate

class CreateVaccinations {
    data class ClientRequest(val vaccinations: List<VaccinationRequest>) {

        data class VaccinationRequest(
            val vaccineName: String,
            val doseRound: Long,
            val doseDescription: String?,
            val vaccinatedDate: LocalDate,
            val vaccinationFacility: String?,
            val vaccineManufacturer: String?,
            val vaccineProductName: String?,
            val vaccineLotNumber: String?
        )
    }
}
