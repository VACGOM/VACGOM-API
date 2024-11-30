package kr.co.vacgom.persistence.vaccination.entity;

import jakarta.persistence.*;
import kr.co.vacgom.persistence.global.entity.BaseEntity;
import kr.co.vacgom.persistence.vaccination.entity.constants.VaccineType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Table(name = "TB_VACCINE")
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class VaccineEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "VACCINE_ID",
            nullable = false,
            updatable = false
    )
    @Comment("[Not Null] 백신 엔티티 Id")
    private Long id;

    @Column(nullable = false)
    @Comment("[Not Null] 백곰에서 분류한 백신 고유 이름")
    private String name;

    @Column(nullable = false)
    @Comment("[Not Null] 최소 접종 차수")
    private Long minDoseRound;

    @Comment("[Not Null] 최대 접종 차수")
    private Long maxDoseRound;

    @Column(nullable = false)
    @Enumerated(STRING)
    @Comment("[Not Null] 백신 타입 (국가예방접종, 일반예방접종, 기타 이벤트)")
    private VaccineType type;
}
