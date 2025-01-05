package kr.co.vacgom.api.vaccine.domain

import jakarta.persistence.*
import kr.co.vacgom.api.global.common.domain.BaseTimeEntity
import kr.co.vacgom.api.vaccine.domain.constants.VaccineType
import org.hibernate.annotations.Comment


@Entity
@Table(name = "TB_VACCINE")
class Vaccine(
    @Column(nullable = false)
    @Comment("[Not Null] 백곰에서 분류한 백신 고유 이름")
    val name: String,

    @Column(nullable = false)
    @Comment("[Not Null] 최소 접종 차수")
    val minDoseRound: Long,

    @Comment("[Not Null] 최대 접종 차수")
    val maxDoseRound: Long,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("[Not Null] 백신 타입 (국가예방접종, 일반예방접종, 기타 이벤트)")
    val type: VaccineType
) : BaseTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    @Comment("[Not Null] 백신 엔티티 Id")
    val id: Long? = null
}
