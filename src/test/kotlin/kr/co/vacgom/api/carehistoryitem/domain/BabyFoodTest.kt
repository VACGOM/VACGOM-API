package kr.co.vacgom.api.carehistoryitem.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import java.time.LocalDateTime

class BabyFoodTest : FunSpec({
    test("BabyFood 객체 정상 생성 테스트") {
        val amount = 100
        val executionDate = LocalDateTime.now()

        val babyFood = BabyFood(
            amount = amount,
            executionDate = executionDate,
            itemType = CareHistoryItemType.BABY_FOOD
        )

        babyFood.executionDate shouldBe executionDate
        babyFood.amount shouldBe amount
    }
})
