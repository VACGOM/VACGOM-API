package kr.co.vacgom.api.user.domain

data class ManagerGroup(
    val adminManager: User,
    val managers: MutableSet<User>,
) {
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
