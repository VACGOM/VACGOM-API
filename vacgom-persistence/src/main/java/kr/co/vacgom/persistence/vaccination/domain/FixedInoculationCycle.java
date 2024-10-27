package kr.co.vacgom.persistence.vaccination.domain;

import jakarta.persistence.*;
import kr.co.vacgom.persistence.global.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "TB_FIXED_INOCULATION_CYCLE")
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class FixedInoculationCycle extends BaseEntity {

    @Id
    @Column(name = "FIXED_INOCULATION_CYCLE_ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private Long inoculationOrder;

    @Column(nullable = false)
    private Long startMonthCnt;

    @Column(nullable = false)
    private Long endMonthCnt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VACCINATION_ID")
    private VaccinationEntity vaccination;
}
