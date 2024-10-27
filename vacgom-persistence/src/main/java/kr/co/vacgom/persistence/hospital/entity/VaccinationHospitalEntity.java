package kr.co.vacgom.persistence.hospital.entity;

import jakarta.persistence.*;
import kr.co.vacgom.persistence.vaccination.domain.Vaccination;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "TB_VACCINATION_HOSPITAL")
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class VaccinationHospitalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VACCINATION_HOSPITAL_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HOSPITAL_ID")
    private HospitalEntity hospital;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VACCINATION_ID")
    private Vaccination vaccination;
}
