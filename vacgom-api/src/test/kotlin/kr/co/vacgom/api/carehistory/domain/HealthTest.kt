package kr.co.vacgom.api.carehistory.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class HealthTest : FunSpec({
    test("Health 객체 정상 생성 테스트") {
        val temperature = 36.5
        val memo = "정상 온도넹"
        val executionDate = LocalDateTime.now()

        val health = Health(temperature = temperature, memo = memo, executionDate = executionDate)

        health.temperature shouldBe temperature
        health.memo shouldBe memo
        health.executionDate shouldBe executionDate
    }
})
