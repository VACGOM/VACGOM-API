package kr.co.vacgom.api.carehistoryitem.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kr.co.vacgom.api.baby.domain.Baby
import kr.co.vacgom.api.baby.domain.enums.Gender
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import java.time.LocalDate
import java.time.LocalDateTime

class SleepTest : FunSpec({
    test("Sleep 객체 정상 생성 테스트") {
        val startTime = LocalDateTime.now()
        val endTime = LocalDateTime.now().plusHours(8)
        val minutes = 30

        val baby = Baby(
            name = "백곰 아기",
            profileImg = "아기 이미지",
            gender = Gender.MALE,
            birthday = LocalDate.now(),
        )

        val sleep = Sleep(
            minutes = minutes,
            startTime = startTime,
            endTime = endTime,
            baby = baby,
            itemType = CareHistoryItemType.SLEEP,
            executionTime = startTime
        )

        sleep.minutes shouldBe minutes
        sleep.executionTime shouldBe startTime
        sleep.itemType shouldBe CareHistoryItemType.SLEEP
        sleep.startTime shouldBe startTime
        sleep.endTime shouldBe endTime
    }
})
