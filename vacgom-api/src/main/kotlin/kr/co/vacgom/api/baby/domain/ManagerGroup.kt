package kr.co.vacgom.api.baby.domain

import kr.co.vacgom.api.global.common.domain.BaseTimeEntity
import kr.co.vacgom.api.user.domain.User

data class ManagerGroup(
    val adminManager: User,
    val managers: MutableSet<User>,
): BaseTimeEntity() {
    companion object {
        fun create(
            adminManager: User,
            managers: MutableSet<User>,
        ): ManagerGroup {
            return ManagerGroup(
                adminManager = adminManager,
                managers = managers,
            )
        }
    }
}
