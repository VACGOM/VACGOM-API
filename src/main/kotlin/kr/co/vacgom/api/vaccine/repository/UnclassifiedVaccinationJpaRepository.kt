package kr.co.vacgom.api.vaccine.repository

import kr.co.vacgom.api.vaccine.domain.UnclassifiedVaccination
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UnclassifiedVaccinationJpaRepository : JpaRepository<UnclassifiedVaccination, UUID> {
    
}
