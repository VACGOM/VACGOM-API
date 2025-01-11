package kr.co.vacgom.api.carehistoryitem.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kr.co.vacgom.api.baby.domain.Baby
import kr.co.vacgom.api.baby.domain.enums.Gender
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import java.time.LocalDate
import java.time.LocalDateTime

class BreastFeedingTest : FunSpec({
    test("BreastFeeding 객체 정상 생성 테스트") {
        val leftStartTime = LocalDateTime.now()
        val leftEndTime = LocalDateTime.now().plusMinutes(10)
        val rightStartTime = LocalDateTime.now()
        val rightEndTime = LocalDateTime.now().plusMinutes(10)
        val executionTime = LocalDateTime.now()
        val leftMinutes = 10
        val rightMinutes = 10

        val baby = Baby(
            name = "백곰 아기",
            profileImg = "아기 이미지",
            gender = Gender.MALE,
            birthday = LocalDate.now(),
        )

        val breastFeeding = BreastFeeding(
            leftStartTime = leftStartTime,
            leftEndTime = leftEndTime,
            leftMinutes = leftMinutes,
            rightStartTime = rightStartTime,
            rightEndTime = rightEndTime,
            rightMinutes = rightMinutes,
            baby = baby,
            executionTime = executionTime,
            itemType = CareHistoryItemType.BREAST_FEEDING
        )

        breastFeeding.leftStartTime shouldBe leftStartTime
        breastFeeding.leftEndTime shouldBe leftEndTime
        breastFeeding.leftMinutes shouldBe leftMinutes
        breastFeeding.rightEndTime shouldBe rightEndTime
        breastFeeding.rightMinutes shouldBe rightMinutes
        breastFeeding.executionTime shouldBe executionTime
        breastFeeding.baby shouldBe baby
        breastFeeding.itemType shouldBe CareHistoryItemType.BREAST_FEEDING
    }
})
