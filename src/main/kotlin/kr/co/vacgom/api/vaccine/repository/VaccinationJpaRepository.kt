package kr.co.vacgom.api.vaccine.repository

import kr.co.vacgom.api.vaccine.domain.Vaccination
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface VaccinationJpaRepository : JpaRepository<Vaccination, UUID> {
}
