package kr.co.vacgom.persistence.vaccination.infrastructure;

import kr.co.vacgom.persistence.vaccination.entity.VaccinationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
class VaccinationRepositoryAdapter implements VaccinationRepository {

    private final VaccinationQueryDslRepository vaccinationQueryDslRepository;
    private final VaccinationJpaRepository vaccinationJpaRepository;

    @Override
    public VaccinationEntity save(VaccinationEntity vaccination) {
        return null;
    }

    @Override
    public List<VaccinationEntity> saveAll(List<VaccinationEntity> vaccinations) {
        return List.of();
    }
}
