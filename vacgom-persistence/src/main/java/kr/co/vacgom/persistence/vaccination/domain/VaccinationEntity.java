package kr.co.vacgom.persistence.vaccination.domain;

import jakarta.persistence.*;
import kr.co.vacgom.persistence.global.entity.BaseEntity;
import kr.co.vacgom.persistence.vaccination.domain.constants.VaccinationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "TB_VACCINATION")
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class VaccinationEntity extends BaseEntity {

    @Id
    @Column(name = "VACCINATION_ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private String diseaseName;

    @Column(nullable = false)
    private String vaccineName;

    @Column(nullable = false)
    private Long minOrder;

    @Column(nullable = false)
    private Long maxOrder;

    @Column(columnDefinition = "VARCHAR(500)", nullable = false)
    private String icon;

    @Column(columnDefinition = "VARCHAR(500)", nullable = false)
    private String certificationIcon;

    @Column(columnDefinition = "VARCHAR(500)", nullable = false)
    private String certificationBackgroundImage;

    @Column(columnDefinition = "VARCHAR(500)", nullable = false)
    private String certificationMaskImage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VaccinationType vaccinationType;
}
