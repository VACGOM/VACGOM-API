package kr.co.vacgom.api.carehistoryitem.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import java.time.LocalDateTime

class BabyFormulaTest : FunSpec({
    test("BabyFormula 객체 정상 생성 테스트") {
        val amount = 100
        val executionDate = LocalDateTime.now()

        val babyFormula = BabyFormula(
            amount = amount,
            executionDate = executionDate,
            itemType = CareHistoryItemType.BABY_FOOD
        )

        babyFormula.executionDate shouldBe executionDate
        babyFormula.amount shouldBe amount
    }

})
