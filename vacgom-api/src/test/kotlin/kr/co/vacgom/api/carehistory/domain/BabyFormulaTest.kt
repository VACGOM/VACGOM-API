package kr.co.vacgom.api.carehistory.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class BabyFormulaTest : FunSpec({
    test("BabyFormula 객체 정상 생성 테스트") {
        val amount = 100L
        val executionDate = LocalDateTime.now()

        val babyFormula = BabyFormula(amount = amount, executionDate = executionDate)

        babyFormula.executionDate shouldBe executionDate
        babyFormula.amount shouldBe amount
    }

})
