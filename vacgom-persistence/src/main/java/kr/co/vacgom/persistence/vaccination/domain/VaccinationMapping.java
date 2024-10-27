package kr.co.vacgom.persistence.vaccination.domain;

import jakarta.persistence.*;
import kr.co.vacgom.persistence.global.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "TB_VACCINATION_MAPPING")
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class VaccinationMapping extends BaseEntity {

    @Id
    @Column(name = "VACCINATION_MAPPING_ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VACCINATION_ID")
    private VaccinationEntity vaccination;

    @Column(nullable = false)
    private String value;
}

