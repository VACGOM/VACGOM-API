package kr.co.vacgom.api.carehistory.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kr.co.vacgom.api.carehistory.domain.enums.ExcrementType
import java.time.LocalDateTime

class DiaperTest : FunSpec({
    test("Diaper 객체 정상 생성 테스트") {
        val excrementType = ExcrementType.POO
        val executionDate = LocalDateTime.now()

        val diaper = Diaper(excrementType = excrementType, executionDate = executionDate)

        diaper.executionDate shouldBe executionDate
        diaper.executionDate shouldBe executionDate
    }
})
