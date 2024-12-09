package kr.co.vacgom.api.baby.domain

import kr.co.vacgom.api.global.common.domain.BaseTimeEntity
import kr.co.vacgom.api.global.util.UuidCreator
import kr.co.vacgom.api.user.domain.enums.Gender
import java.time.LocalDate
import java.util.*

data class Baby(
    val id: UUID = UuidCreator.create(),
    val name: String,
    val profileImg: String?,
    val gender: Gender,
    val birthday: LocalDate,
    val managerGroup: ManagerGroup
): BaseTimeEntity() {
    companion object {
        fun create(
            name: String,
            profileImg: String?,
            gender: Gender,
            birthday: LocalDate,
            managerGroup: ManagerGroup
        ): Baby {
            return Baby(
                name = name,
                profileImg = profileImg,
                gender = gender,
                birthday = birthday,
                managerGroup = managerGroup
            )
        }
    }
}
