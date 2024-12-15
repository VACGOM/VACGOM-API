package kr.co.vacgom.api.babymanager.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kr.co.vacgom.api.auth.oauth.enums.SocialLoginProvider
import kr.co.vacgom.api.baby.domain.Baby
import kr.co.vacgom.api.user.domain.User
import kr.co.vacgom.api.user.domain.enums.UserRole

class BabyManagerTest : FunSpec({
    test("BabyManager 객체 정상 생성 테스트") {
        val user = User(
            nickname = "nickname",
            socialId = "socialId",
            provider = SocialLoginProvider.KAKAO,
            role = UserRole.ROLE_USER
        )

        val baby = Baby(
            name = "name",
            profileImg = "profileImgUrl",
            gender = kr.co.vacgom.api.baby.domain.enums.Gender.MALE,
            birthday = java.time.LocalDate.now()
        )
        
        val babyManager = BabyManager(
            user = user,
            baby = baby,
            isAdmin = true
        )

        babyManager.user shouldBe user
        babyManager.baby shouldBe baby
        babyManager.isAdmin shouldBe true
    }
})
