package kr.co.vacgom.api.carehistoryitem.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kr.co.vacgom.api.carehistoryitem.domain.enums.BreastDirection
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import java.time.LocalDateTime

class BreastFeedingTest : FunSpec({
    test("BreastFeeding 객체 정상 생성 테스트") {
        val startDate = LocalDateTime.now()
        val endDate = LocalDateTime.now().plusMinutes(10)
        val minutes = 10
        val breastDirection = BreastDirection.LEFT

        val breastFeeding = BreastFeeding(
            startDate = startDate,
            endDate = endDate,
            minutes = minutes,
            breastDirection = breastDirection,
            executionDate = startDate,
            itemType = CareHistoryItemType.BATH
        )

        breastFeeding.startDate shouldBe startDate
        breastFeeding.endDate shouldBe endDate
        breastFeeding.minutes shouldBe minutes
        breastFeeding.breastDirection shouldBe breastDirection
    }
})
