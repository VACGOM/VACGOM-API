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
)
