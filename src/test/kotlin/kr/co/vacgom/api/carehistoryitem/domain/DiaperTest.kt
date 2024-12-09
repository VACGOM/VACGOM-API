package kr.co.vacgom.api.carehistoryitem.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import kr.co.vacgom.api.carehistoryitem.domain.enums.ExcrementType
import java.time.LocalDateTime

class DiaperTest : FunSpec({
    test("Diaper 객체 정상 생성 테스트") {
        val excrementType = ExcrementType.POO
        val executionTime = LocalDateTime.now()

        val diaper = Diaper(
            excrementType = excrementType,
            itemType = CareHistoryItemType.DIAPER,
            executionTime = executionTime
        )

        diaper.executionTime shouldBe executionTime
        diaper.executionTime shouldBe executionTime
    }
})
