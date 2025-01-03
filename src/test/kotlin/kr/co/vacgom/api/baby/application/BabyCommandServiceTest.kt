package kr.co.vacgom.api.baby.application

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kr.co.vacgom.api.baby.domain.Baby
import kr.co.vacgom.api.baby.domain.enums.Gender
import kr.co.vacgom.api.baby.presentation.dto.BabyDto
import kr.co.vacgom.api.baby.repository.BabyRepository
import kr.co.vacgom.api.babymanager.application.BabyManagerService
import kr.co.vacgom.api.user.application.UserQueryService
import java.time.LocalDate

class BabyCommandServiceTest : DescribeSpec({
    val babyRepositoryMock: BabyRepository = mockk(relaxed = true)
    val babyManagerServiceMock: BabyManagerService = mockk(relaxed = true)
    val userQueryServiceMock: UserQueryService = mockk(relaxed = true)

    val sut = BabyCommandService(
        babyManagerService = babyManagerServiceMock,
        babyRepository = babyRepositoryMock,
        userQueryService = userQueryServiceMock,
    )
    describe("아이 정보 수정 테스트") {
        context("아이가 존재하는 경우") {
            it("정상적으로 정보 수정이 이루어진다.") {
                every { babyRepositoryMock.findById(baby.id) } returns baby

                val updateName = "name"
                val updateProfileImg = "profileImgUrl"
                val updateGender = Gender.MALE
                val updateBirthday = LocalDate.of(2022, 1, 1)

                val request = BabyDto.Request.Update(updateName, updateProfileImg, updateGender, updateBirthday)

                val result = sut.updateBabyInfo(baby.id, request)

                result.name shouldBe updateName
                result.profileImg shouldBe updateProfileImg
                result.gender shouldBe updateGender
                result.birthday shouldBe updateBirthday
            }
        }
    }
}) {
    companion object {
        val baby = Baby(
            name = "name",
            profileImg = "profileImg",
            gender = Gender.MALE,
            birthday = LocalDate.of(2021, 3, 9),
        )

        private val birthdays = listOf(
            LocalDate.of(2023, 12, 25),
            LocalDate.of(2021, 1, 1)
        )

        val babies = enumValues<Gender>().mapIndexed { index, gender ->
            Baby(
                name = "name",
                profileImg = "profileImg",
                gender = gender,
                birthday = birthdays[index],
            )
        }
    }
}
