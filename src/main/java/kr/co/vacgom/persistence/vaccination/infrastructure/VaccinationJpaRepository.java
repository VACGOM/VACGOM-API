package kr.co.vacgom.persistence.vaccination.infrastructure;

import kr.co.vacgom.persistence.vaccination.entity.VaccinationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface VaccinationJpaRepository extends JpaRepository<VaccinationEntity, UUID> {
}
