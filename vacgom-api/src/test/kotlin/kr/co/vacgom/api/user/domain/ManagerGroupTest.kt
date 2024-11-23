package kr.co.vacgom.api.user.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kr.co.vacgom.api.auth.oauth.enums.SocialLoginProvider

class ManagerGroupTest : FunSpec({
    test("ManagerGroup 정상 생성 테스트") {
        val adminUser = User.create(
            nickname = "admin",
            socialId = "socialId",
            provider = SocialLoginProvider.KAKAO,
            roles = emptyList(),
        )

        val managers = (0 until 3).map {
            User.create(
                nickname = "manager$it",
                socialId = "socialId",
                provider = SocialLoginProvider.KAKAO,
                roles = emptyList(),
            )
        }.toMutableSet()

        val managerGroup = ManagerGroup.create(adminUser, managers)

        managerGroup.adminManager shouldBe adminUser
        managerGroup.managers shouldBe managers
        managerGroup.managers.size shouldBe managers.size
    }
})
