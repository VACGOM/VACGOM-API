package kr.co.vacgom.api.carehistory.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class SleepTest : FunSpec({
    test("Sleep 객체 정상 생성 테스트") {
        val startDate = LocalDateTime.now()
        val endDate = LocalDateTime.now().plusHours(8)

        val sleep = Sleep(startDate = startDate, endDate = endDate)

        sleep.startDate shouldBe startDate
        sleep.endDate shouldBe endDate
    }
})
