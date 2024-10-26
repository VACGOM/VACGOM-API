package kr.co.vacgom.persistence.vaccination.domain;

import jakarta.persistence.*;
import kr.co.vacgom.persistence.global.BaseEntity;
import kr.co.vacgom.persistence.vaccination.domain.constants.VaccinationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_vaccination")
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Builder
public class Vaccination extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccination_id")
    private Long id;

    @Column(nullable = false)
    private String diseaseName;

    @Column(nullable = false)
    private String vaccineName;

    @Column(nullable = false)
    private Long minOrder;

    @Column(nullable = false)
    private Long maxOrder;

    @Column(columnDefinition = "VARCHAR(1000)", nullable = false)
    private String icon;

    @Column(columnDefinition = "VARCHAR(1000)", nullable = false)
    private String certificationIcon;

    @Column(columnDefinition = "VARCHAR(1000)", nullable = false)
    private String certificationBackgroundImage;

    @Column(columnDefinition = "VARCHAR(1000)", nullable = false)
    private String certificationMaskImage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VaccinationType vaccinationType;
}
