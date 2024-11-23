package kr.co.vacgom.api.carehistory.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class BreastPumpingTest : FunSpec({
    test("BreastPumping 객체 정상 생성 테스트") {
        val amount = 100L
        val executionDate = LocalDateTime.now()

        val breastPumping = BreastPumping(amount = amount, executionDate = executionDate)

        breastPumping.amount shouldBe amount
        breastPumping.executionDate shouldBe executionDate
    }
})
