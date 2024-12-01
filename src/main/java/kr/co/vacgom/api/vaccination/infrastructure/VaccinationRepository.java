package kr.co.vacgom.api.vaccination.infrastructure;

import kr.co.vacgom.api.vaccination.entity.VaccinationEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccinationRepository {

    VaccinationEntity save(VaccinationEntity vaccination);

    List<VaccinationEntity> saveAll(List<VaccinationEntity> vaccinations);
}
