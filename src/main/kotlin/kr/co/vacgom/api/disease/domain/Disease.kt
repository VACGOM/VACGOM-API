package kr.co.vacgom.api.disease.domain

import jakarta.persistence.*
import kr.co.vacgom.api.global.common.domain.BaseTimeEntity
import org.hibernate.annotations.Comment

@Entity
@Table(name = "TB_DISEASE")
class Disease(

) : BaseTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("[Not Null] 질병 Id")
    val id: Long? = null

    @Column(nullable = false)
    @Comment("[Not Null] 질병 Id")
    val name: String = String()

    @Column(nullable = false, columnDefinition = "varchar(500)")
    @Comment("[Not Null] 질병 설명 정보")
    val description: String = String()
}
