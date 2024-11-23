package kr.co.vacgom.api.carehistory.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class BathTest : FunSpec({
    test("Bath 객체 정상 생성 테스트") {
        val startDate = LocalDateTime.now()
        val endDate = LocalDateTime.now().plusMinutes(30)

        val bath = Bath(startDate = startDate, endDate = endDate)

        bath.startDate shouldBe startDate
        bath.endDate shouldBe endDate
    }
})
