package kr.co.vacgom.api.vaccination.domain

import java.time.LocalDate

data class Vaccination(
    val doseRound: Long,
    val doseDescription: String,
    val vaccinatedDate: LocalDate,
    val vaccinationFacility: String?,
    val vaccinationManufacturer: String?,
    val vaccineProductName: String?,
    val vaccineLotNumber: String?
) {
    companion object {
        fun create(
            doseRound: Long,
            doseDescription: String,
            vaccinatedDate: LocalDate,
            vaccinationFacility: String? = null,
            vaccinationManufacturer: String? = null,
            vaccineProductName: String? = null,
            vaccineLotNumber: String? = null
        ): Vaccination {
            return Vaccination(
                doseRound,
                doseDescription,
                vaccinatedDate,
                vaccinationFacility,
                vaccinationManufacturer,
                vaccineProductName,
                vaccineLotNumber
            )
        }
    }
}
