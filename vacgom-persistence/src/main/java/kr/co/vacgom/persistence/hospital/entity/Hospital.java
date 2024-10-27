package kr.co.vacgom.persistence.hospital.entity;

import jakarta.persistence.*;
import kr.co.vacgom.persistence.global.entity.BaseEntity;
import kr.co.vacgom.persistence.hospital.entity.constants.HospitalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_HOSPITAL")
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Builder
public class Hospital extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_id")
    private Long id;

    private String name;
    private String telNo;
    private String address;

    @Enumerated(EnumType.STRING)
    private HospitalType type;

    private Double latitude;
    private Double longitude;

    @OneToMany(
            mappedBy = "hospital",
            cascade = CascadeType.REMOVE
    )
    private List<VaccinationHospital> supportVaccinations = new ArrayList<>();
}
