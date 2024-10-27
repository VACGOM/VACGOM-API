package kr.co.vacgom.persistence.vaccination.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import kr.co.vacgom.persistence.global.entity.BaseEntity;
import kr.co.vacgom.persistence.member.entity.BabyEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_inoculation")
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class Inoculation extends BaseEntity {

    @Id
    @Column(name = "inoculation_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private Long inoculationOrder;

    @Column(nullable = false)
    private String inoculationOrderString;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String agency;

    private String vaccineName;

    private String vaccineBrandName;

    private String lotNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "baby_id")
    private BabyEntity baby;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vaccination_id")
    private VaccinationEntity vaccination;
}
