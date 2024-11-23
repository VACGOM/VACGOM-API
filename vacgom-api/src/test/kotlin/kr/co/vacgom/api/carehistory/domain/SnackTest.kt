package kr.co.vacgom.api.carehistory.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class SnackTest : FunSpec({
    test("Snack 객체 정상 생성 테스트") {
        val memo = "과자 5봉지나 먹음"
        val executionDate = LocalDateTime.now()
        val snack = Snack(memo = memo, executionDate = executionDate)

        snack.memo shouldBe memo
        snack.executionDate shouldBe executionDate
    }
})
