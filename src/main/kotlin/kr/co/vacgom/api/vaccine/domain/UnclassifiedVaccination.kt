package kr.co.vacgom.api.vaccine.domain

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING
import jakarta.persistence.*
import jakarta.persistence.FetchType.LAZY
import kr.co.vacgom.api.baby.domain.Baby
import kr.co.vacgom.api.global.common.domain.BaseTimeEntity
import kr.co.vacgom.api.global.util.UuidCreator
import org.hibernate.annotations.Comment
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "TB_VACCINATION_UNCLASSIFIED")
class UnclassifiedVaccination(
    @Column(nullable = false)
    @Comment("[Not Null] 미분류 백신 이름")
    val name: String,

    @Column
    @Comment("[Nullable] 백신 접종 차수")
    val doseRound: Long?,

    @Column
    @Comment("[Nullable] 백신 접종 차수 및 접종 정보")
    val doseRoundDescription: String?,

    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    @Comment("[Not Null] 백신 접종 일자")
    val vaccinatedAt: LocalDate,

    @Comment("[Nullable] 백신 접종 기관")
    val facility: String?,

    @Comment("[Nullable] 백신 제조사")
    val manufacturer: String?,

    @Comment("[Nullable] 판매 허가된 백신 정규 명칭")
    val productName: String?,

    @Comment("[Nullable] 백신 고유 로트번호")
    val lotNumber: String?,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "BABY_ID")
    @Comment("[NotNull] 아기 Id")
    val baby: Baby
) : BaseTimeEntity() {

    @Id
    @Column(
        columnDefinition = "BINARY(16)",
        nullable = false,
        updatable = false
    )
    @Comment("[Not Null] 미분류 백신 접종 내역 엔티티 Id")
    val id: UUID = UuidCreator.create()
}
