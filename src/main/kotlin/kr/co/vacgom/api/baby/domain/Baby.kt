package kr.co.vacgom.api.baby.domain

import jakarta.persistence.*
import kr.co.vacgom.api.baby.domain.enums.Gender
import kr.co.vacgom.api.babymanager.domain.BabyManager
import kr.co.vacgom.api.carehistoryitem.domain.CareHistoryItem
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import kr.co.vacgom.api.global.common.domain.BaseTimeEntity
import kr.co.vacgom.api.global.util.UuidCreator
import kr.co.vacgom.api.vaccine.domain.UnclassifiedVaccination
import kr.co.vacgom.api.vaccine.domain.Vaccination
import org.hibernate.annotations.Comment
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "TB_BABY")
class Baby(
    id: UUID = UuidCreator.create(),
    name: String,
    profileImg: String?,
    gender: Gender,
    birthday: LocalDate,
): BaseTimeEntity() {
    @Id
    @Column(name = "baby_id", nullable = false, updatable = false)
    @Comment("[Not Null] 아기 id")
    var id: UUID = id
        protected set

    @Column(nullable = false)
    @Comment("[Not Null] 아기 이름")
    var name: String = name
        protected set

    @Comment("[Not Null] 아기 프로필 이미지 url")
    var profileImg: String? = profileImg
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

    @OneToMany(mappedBy = "baby", cascade = [(CascadeType.REMOVE)])
    val babyManagers: List<BabyManager> = listOf()

    @OneToMany(mappedBy = "baby", cascade = [(CascadeType.REMOVE)])
    @MapKey(name = "itemType")
    val careHistoryItems: Map<CareHistoryItemType, CareHistoryItem> = mapOf()

    @OneToMany(mappedBy = "baby", cascade = [(CascadeType.REMOVE)])
    val vaccinations: List<Vaccination> = listOf()

    @OneToMany(mappedBy = "baby", cascade = [(CascadeType.REMOVE)])
    val unclassifiedVaccinations: List<UnclassifiedVaccination> = listOf()

    fun update(
        name: String,
        profileImg: String?,
        gender: Gender,
        birthday: LocalDate,
    ): Baby {
        this.name = name
        this.profileImg = profileImg
        this.gender = gender
        this.birthday = birthday

        return this
    }
}
