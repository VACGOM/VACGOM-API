package kr.co.vacgom.api.baby.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kr.co.vacgom.api.auth.oauth.enums.SocialLoginProvider
import kr.co.vacgom.api.user.domain.User
import kr.co.vacgom.api.user.domain.enums.Gender
import java.time.LocalDate

class BabyTest : FunSpec({
    test("Baby 객체 정상 생성 테스트") {
        val name = "name"
        val profileImg = "profileImgUrl"
        val gender = Gender.MALE
        val birthday = LocalDate.now()
        val managerGroup = ManagerGroup.create(
            adminManager = User.create(
                nickname = "nickname",
                socialId = "socialId",
                provider = SocialLoginProvider.KAKAO,
                roles = emptyList(),
            ),
            managers = mutableSetOf()
        )

        val baby = Baby.create(
            name,
            profileImg,
            gender,
            birthday,
            managerGroup
        )

        baby.name shouldBe name
        baby.profileImg shouldBe profileImg
        baby.gender shouldBe gender
        baby.birthday shouldBe birthday
        baby.managerGroup shouldBe managerGroup

        //Todo: 추후 Baby 도메인 제약 사항 추가 시 검증 필요
    }
})
