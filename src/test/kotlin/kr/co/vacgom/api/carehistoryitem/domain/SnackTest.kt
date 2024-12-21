package kr.co.vacgom.api.carehistoryitem.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kr.co.vacgom.api.baby.domain.Baby
import kr.co.vacgom.api.baby.domain.enums.Gender
import kr.co.vacgom.api.carehistoryitem.domain.enums.CareHistoryItemType
import java.time.LocalDate
import java.time.LocalDateTime

class SnackTest : FunSpec({
    test("Snack 객체 정상 생성 테스트") {
        val memo = "과자 5봉지나 먹음. 크게 성장할 듯"
        val executionTime = LocalDateTime.now()

        val baby = Baby(
            name = "백곰 아기",
            profileImg = "아기 이미지",
            gender = Gender.MALE,
            birthday = LocalDate.now(),
        )

        val snack = Snack(
            memo = memo,
            baby = baby,
            itemType = CareHistoryItemType.SNACK,
            executionTime = executionTime
        )

        snack.memo shouldBe memo
        snack.executionTime shouldBe executionTime
    }
})
