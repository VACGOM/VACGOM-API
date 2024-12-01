package kr.co.vacgom.api.vaccination.infrastructure;

import kr.co.vacgom.api.vaccination.entity.VaccinationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface VaccinationJpaRepository extends JpaRepository<VaccinationEntity, UUID> {
}
