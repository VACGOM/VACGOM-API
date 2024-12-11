package kr.co.vacgom.api.carehistoryitem.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kr.co.vacgom.api.carehistoryitem.domain.enums.BreastDirection
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import java.time.LocalDateTime

class BreastFeedingTest : FunSpec({
    test("BreastFeeding 객체 정상 생성 테스트") {
        val startTime = LocalDateTime.now()
        val endTime = LocalDateTime.now().plusMinutes(10)
        val minutes = 10
        val breastDirection = BreastDirection.LEFT

        val breastFeeding = BreastFeeding(
            startTime = startTime,
            endTime = endTime,
            minutes = minutes,
            breastDirection = breastDirection,
            executionTime = startTime,
            itemType = CareHistoryItemType.BATH
        )

        breastFeeding.startTime shouldBe startTime
        breastFeeding.endTime shouldBe endTime
        breastFeeding.minutes shouldBe minutes
        breastFeeding.breastDirection shouldBe breastDirection
    }
})
