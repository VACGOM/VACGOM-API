package kr.co.vacgom.persistence.vaccination.domain;

import jakarta.persistence.*;
import kr.co.vacgom.persistence.global.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_FIXED_INOCULATION_CYCLE")
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Builder
public class FixedInoculationCycle extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fixed_inoculation_cycle_id")
    private Long id;

    @Column(nullable = false)
    private Long inoculationOrder;

    @Column(nullable = false)
    private Long startMonthCnt;

    @Column(nullable = false)
    private Long endMonthCnt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vaccination_id")
    private Vaccination vaccination;
}
