package kr.co.vacgom.api.carehistoryitem.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import java.time.LocalDateTime

class SleepTest : FunSpec({
    test("Sleep 객체 정상 생성 테스트") {
        val startDate = LocalDateTime.now()
        val endDate = LocalDateTime.now().plusHours(8)
        val minutes = 30

        val sleep = Sleep(
            minutes = minutes,
            startDate = startDate,
            endDate = endDate,
            itemType = CareHistoryItemType.SLEEP,
            executionDate = startDate
        )

        sleep.minutes shouldBe minutes
        sleep.executionDate shouldBe startDate
        sleep.itemType shouldBe CareHistoryItemType.SLEEP
        sleep.startDate shouldBe startDate
        sleep.endDate shouldBe endDate
    }
})
