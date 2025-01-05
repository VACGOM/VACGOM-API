package kr.co.vacgom.api.vaccine.domain

import jakarta.persistence.*
import kr.co.vacgom.api.disease.domain.Disease
import kr.co.vacgom.api.global.common.domain.BaseTimeEntity
import org.hibernate.annotations.Comment

@Entity
@Table(name = "TB_VACCINE_DISEASE_MAP")
class VaccineDiseaseMap private constructor(
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("[Not Null] 질병-백신 매핑 엔티티 Id")
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VACCINE_ID")
    @Comment("[Not Null] 백신 Id")
    val vaccine: Vaccine,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DISEASE_ID")
    @Comment("[Not Null] 예방 가능한 질병 Id")
    val disease: Disease
) : BaseTimeEntity()
