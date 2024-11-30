package kr.co.vacgom.api.vaccination.domain

import java.time.LocalDate

data class Vaccination(
    val doseRound: Long,
    val doseRoundDescription: String,
    val vaccinatedAt: LocalDate,
    val facility: String?,
    val manufacturer: String?,
    val productName: String?,
    val lotNumber: String?
)
