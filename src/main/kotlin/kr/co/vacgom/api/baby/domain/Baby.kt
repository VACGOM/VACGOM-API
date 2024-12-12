package kr.co.vacgom.api.baby.domain

import jakarta.persistence.*
import kr.co.vacgom.api.baby.domain.enums.Gender
import kr.co.vacgom.api.global.common.domain.BaseTimeEntity
import kr.co.vacgom.api.global.util.UuidCreator
import org.hibernate.annotations.Comment
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "TB_BABY")
class Baby(
    id: UUID = UuidCreator.create(),
    name: String,
    profileImg: String,
    gender: Gender,
    birthday: LocalDate,
): BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "baby_id", nullable = false, updatable = false)
    @Comment("[Not Null] 아기 id")
    var id: UUID = id
        protected set

    @Column(nullable = false)
    @Comment("[Not Null] 아기 이름")
    var name: String = name
        protected set

    @Comment("[Not Null] 아기 프로필 이미지 url")
    var profileImg: String = profileImg
        protected set

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Comment("[Not Null] 아기 성별")
    var gender: Gender = gender
        protected set

    @Column(nullable = false)
    @Comment("[Not Null] 아기 생년월일")
    var birthday: LocalDate = birthday
        protected set
}
