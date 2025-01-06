package kr.co.vacgom.api.vaccine.repository.vaccine

import kr.co.vacgom.api.vaccine.domain.Vaccine
import org.springframework.data.jpa.repository.JpaRepository

interface VaccineJpaRepository : JpaRepository<Vaccine, Long> {

    fun findByName(name: String): Vaccine?
}
