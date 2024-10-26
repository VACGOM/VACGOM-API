package kr.co.vacgom.persistence.vaccination;

import jakarta.persistence.*;
import kr.co.vacgom.persistence.global.BaseEntity;
import kr.co.vacgom.persistence.vaccination.domain.Vaccination;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_vaccination_mapping")
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Builder
public class VaccinationMapping extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccination_mapping_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vaccination_id")
    private Vaccination vaccination;

    @Column(nullable = false)
    private String value;
}

