package kr.co.vacgom.api.carehistoryitem.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class BathTest : FunSpec({
    test("Bath 객체 정상 생성 테스트") {
        val startDate = LocalDateTime.now()
        val endDate = LocalDateTime.now().plusMinutes(30)

        val bath = Bath.create(
            startDate = startDate,
            endDate = endDate,
            minutes = ChronoUnit.MINUTES.between(startDate, endDate).toInt(),
            executionDate = startDate,
            itemType = CareHistoryItemType.BATH
        )

        bath.startDate shouldBe startDate
        bath.endDate shouldBe endDate
    }

    test("temp test"){
        val startDate = LocalDateTime.now()
        val endDate = LocalDateTime.now().plusMinutes(30)
        println(ChronoUnit.HOURS.between(startDate, endDate))
    }
})
