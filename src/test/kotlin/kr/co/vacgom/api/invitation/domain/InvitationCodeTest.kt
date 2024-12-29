package kr.co.vacgom.api.invitation.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kr.co.vacgom.api.global.util.UuidCreator

class InvitationCodeTest : FunSpec({

    test("InvitationCode 객체 정상 생성 테스트(TTL 지정 X)") {
        val key = UuidCreator.create().toString()
        val userId = UuidCreator.create()
        val babyIds = listOf(UuidCreator.create(), UuidCreator.create())

        val invitationCode = InvitationCode(
            key = key,
            userId = userId,
            babyIds = babyIds
        )

        invitationCode.key shouldBe key
        invitationCode.userId shouldBe userId
        invitationCode.babyIds shouldBe babyIds
    }

    test("InvitationCode 객체 정상 생성 테스트(TTL 지정 O)") {
        val key = UuidCreator.create().toString()
        val userId = UuidCreator.create()
        val babyIds = listOf(UuidCreator.create(), UuidCreator.create())
        val ttl = 100L

        val invitationCode = InvitationCode(
            key = key,
            userId = userId,
            babyIds = babyIds,
            timeToLive = ttl
        )

        invitationCode.key shouldBe key
        invitationCode.userId shouldBe userId
        invitationCode.babyIds shouldBe babyIds
        invitationCode.timeToLive shouldBe ttl
    }
})
