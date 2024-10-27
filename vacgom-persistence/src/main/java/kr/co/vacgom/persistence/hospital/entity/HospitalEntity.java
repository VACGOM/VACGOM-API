package kr.co.vacgom.persistence.hospital.entity;

import jakarta.persistence.*;
import kr.co.vacgom.persistence.global.entity.BaseEntity;
import kr.co.vacgom.persistence.hospital.entity.constants.HospitalType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_HOSPITAL")
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HospitalEntity extends BaseEntity {

    @Id
    @Column(name = "HOSPITAL_ID", columnDefinition = "BINARY(16)")
    private UUID id;

    private String name;
    private String telNo;
    private String address;

    @Enumerated(EnumType.STRING)
    private HospitalType type;

    private Double latitude;
    private Double longitude;

    @OneToMany(
            mappedBy = "hospital",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY
    )
    private List<VaccinationHospitalEntity> supportVaccinations = new ArrayList<>();
}
