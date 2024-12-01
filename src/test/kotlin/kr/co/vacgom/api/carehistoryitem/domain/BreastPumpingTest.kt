package kr.co.vacgom.api.carehistoryitem.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import java.time.LocalDateTime

class BreastPumpingTest : FunSpec({
    test("BreastPumping 객체 정상 생성 테스트") {
        val amount = 100
        val executionDate = LocalDateTime.now()

        val breastPumping = BreastPumping(
            amount = amount,
            itemType = CareHistoryItemType.BREAST_PUMPING,
            executionDate = executionDate
        )

        breastPumping.amount shouldBe amount
        breastPumping.executionDate shouldBe executionDate
    }
})