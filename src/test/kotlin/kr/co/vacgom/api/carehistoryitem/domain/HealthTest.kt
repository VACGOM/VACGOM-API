package kr.co.vacgom.api.carehistoryitem.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kr.co.vacgom.api.baby.domain.Baby
import kr.co.vacgom.api.baby.domain.enums.Gender
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import java.time.LocalDate
import java.time.LocalDateTime

class HealthTest : FunSpec({
    test("Health 객체 정상 생성 테스트") {
        val temperature = 36.5
        val memo = "정상 온도넹"
        val executionTime = LocalDateTime.now()

        val baby = Baby(
            name = "백곰 아기",
            profileImg = "아기 이미지",
            gender = Gender.MALE,
            birthday = LocalDate.now(),
        )

        val health = Health(
            temperature = temperature,
            memo = memo,
            baby = baby,
            itemType = CareHistoryItemType.DIAPER,
            executionTime = executionTime
        )

        health.temperature shouldBe temperature
        health.memo shouldBe memo
        health.executionTime shouldBe executionTime
    }
})
