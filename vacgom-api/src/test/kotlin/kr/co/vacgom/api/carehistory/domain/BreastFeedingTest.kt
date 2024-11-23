package kr.co.vacgom.api.carehistory.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kr.co.vacgom.api.carehistory.domain.enums.BreastDirection
import java.time.LocalDateTime

class BreastFeedingTest : FunSpec({
    test("BreastFeeding 객체 정상 생성 테스트") {
        val startDate = LocalDateTime.now()
        val endDate = LocalDateTime.now().plusMinutes(10)
        val duration = 10L
        val breastDirection = BreastDirection.LEFT

        val breastFeeding = BreastFeeding(
            startDate = startDate,
            endDate = endDate,
            duration = duration,
            breastDirection
        )

        breastFeeding.startDate shouldBe startDate
        breastFeeding.endDate shouldBe endDate
        breastFeeding.duration shouldBe duration
        breastFeeding.breastDirection shouldBe breastDirection
    }
})
