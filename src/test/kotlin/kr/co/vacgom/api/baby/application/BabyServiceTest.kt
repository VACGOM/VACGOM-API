package kr.co.vacgom.api.baby.application 
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import io.mockk.every
import io.mockk.mockk
import kr.co.vacgom.api.baby.domain.Baby
import kr.co.vacgom.api.baby.domain.enums.Gender
import kr.co.vacgom.api.baby.exceptioin.BabyError
import kr.co.vacgom.api.baby.presentation.dto.BabyDto
import kr.co.vacgom.api.baby.repository.BabyRepository
import kr.co.vacgom.api.global.exception.error.BusinessException
import java.time.LocalDate
import java.time.Period

class BabyServiceTest : DescribeSpec({
   val babyRepositoryMock: BabyRepository = mockk(relaxed = true)
    val sut = BabyService(
        babyRepository = babyRepositoryMock,
    )

    describe("아이 다건 조회 테스트") {
     context("아이가 존재하는 경우") {
      it("정상적으로 Baby 객체 List를 반환한다.") {
       every { babyRepositoryMock.findBabiesById(any()) } returns babies

       val result = sut.getBabiesById(babies.map { it.id })

       result shouldBe babies
       result.size shouldBe babies.size
      }
     }

     context("아이가 존재하지 않는 경우") {
      it("Baby Not Found 예외가 발생한다.") {
       every { babyRepositoryMock.findBabiesById(any()) } throws BusinessException(BabyError.BABY_NOT_FOUND)

       val result = shouldThrow<BusinessException> {
           sut.getBabiesById(babies.map { it.id })
       }

       result.message shouldBe BabyError.BABY_NOT_FOUND.message
       result.errorCode shouldBe BabyError.BABY_NOT_FOUND
      }
     }
    }

    describe("아이 단건 조회 테스트") {
     context("아이가 존재하는 경우") {
      it("정상적으로 Baby 객체를 반환한다.") {
       every { babyRepositoryMock.findById(baby.id) } returns baby

       val result = sut.getBabyById(baby.id)

       result shouldBe baby
       result.id shouldBe baby.id
       result.gender shouldBe baby.gender
       result.name shouldBe baby.name
       result.birthday shouldBe baby.birthday
      }
     }

     context("아이 상세 정보 조회 시 아이가 존재하는 경우") {
      it("정상적으로 BabyDetail Dto 객체를 반환한다.") {
       every { babyRepositoryMock.findById(baby.id) } returns baby

       val result = sut.getBabyDetailById(baby.id)

       result.shouldBeTypeOf<BabyDto.Response.Detail>()
       result.id shouldBe baby.id
       result.gender shouldBe baby.gender
       result.profileImg shouldBe baby.profileImg
       result.name shouldBe baby.name
       result.birthday shouldBe baby.birthday
      }
     }

     context("아이 상세 정보 조회(나이 포함) 시 아이가 존재 하는 경우") {
      it("정상적으로 BabyDetailWithAge Dto 객체를 반환한다.") {
       every { babyRepositoryMock.findById(baby.id) } returns baby
       val period = Period.between(baby.birthday, LocalDate.now())

       val result = sut.getBabyDetailWithAgeById(baby.id)

       result.shouldBeTypeOf<BabyDto.Response.DetailWithAge>()
       result.id shouldBe baby.id
       result.gender shouldBe baby.gender
       result.profileImg shouldBe baby.profileImg
       result.name shouldBe baby.name
       result.birthday shouldBe baby.birthday
       result.age.year shouldBe period.years
       result.age.month shouldBe period.months
       result.age.day shouldBe period.days
      }
     }

     context("아이가 존재하지 않는 경우") {
      it("Baby Not Found 예외가 발생한다.") {
       every { babyRepositoryMock.findById(baby.id) } throws BusinessException(BabyError.BABY_NOT_FOUND)

       val result = shouldThrow<BusinessException> { sut.getBabyById(baby.id) }

       result.message shouldBe BabyError.BABY_NOT_FOUND.message
       result.errorCode shouldBe BabyError.BABY_NOT_FOUND
      }
     }

     context("아이 상세 정보 조회 시 아이가 존재하지 않는 경우") {
      it("Baby Not Found 예외가 발생한다.") {
       every { babyRepositoryMock.findById(baby.id) } throws BusinessException(BabyError.BABY_NOT_FOUND)

       val result = shouldThrow<BusinessException> { sut.getBabyDetailById(baby.id) }

       result.message shouldBe BabyError.BABY_NOT_FOUND.message
       result.errorCode shouldBe BabyError.BABY_NOT_FOUND
      }
     }

     context("아이 상세 정보 조회(나이 포함) 시 아이가 존재하지 않는 경우") {
      it("Baby Not Found 예외가 발생한다.") {
       every { babyRepositoryMock.findById(baby.id) } throws BusinessException(BabyError.BABY_NOT_FOUND)

       val result = shouldThrow<BusinessException> { sut.getBabyDetailWithAgeById(baby.id) }

       result.message shouldBe BabyError.BABY_NOT_FOUND.message
       result.errorCode shouldBe BabyError.BABY_NOT_FOUND
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
            LocalDate.of(2023, 12,25),
            LocalDate.of(2021, 1,1)
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
