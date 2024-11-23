package kr.co.vacgom.api.carehistory.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class BabyFoodTest : FunSpec({
    test("BabyFood 객체 정상 생성 테스트") {
        val amount = 100L
        val executionDate = LocalDateTime.now()

        val babyFood = BabyFood(amount = amount, executionDate = executionDate)

        babyFood.executionDate shouldBe executionDate
        babyFood.amount shouldBe amount
    }
})
