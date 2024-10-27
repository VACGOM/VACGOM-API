package kr.co.vacgom.persistence.hospital.entity;

import jakarta.persistence.*;
import kr.co.vacgom.persistence.vaccination.domain.VaccinationEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "TB_VACCINATION_HOSPITAL")
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class VaccinationHospitalEntity {

    @Id
    @Column(name = "VACCINATION_HOSPITAL_ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HOSPITAL_ID")
    private HospitalEntity hospital;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VACCINATION_ID")
    private VaccinationEntity vaccination;
}
