package kr.co.vacgom.api.carehistoryitem.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class BathTest : FunSpec({
    test("Bath 객체 정상 생성 테스트") {
        val startTime = LocalDateTime.now()
        val endTime = LocalDateTime.now().plusMinutes(30)

        val bath = Bath(
            startTime = startTime,
            endTime = endTime,
            minutes = ChronoUnit.MINUTES.between(startTime, endTime).toInt(),
            executionTime = startTime,
            itemType = CareHistoryItemType.BATH
        )

        bath.startTime shouldBe startTime
        bath.endTime shouldBe endTime
    }
})
