package kr.co.vacgom.api.carehistoryitem.application

import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.kotlin.KotlinPlugin
import com.navercorp.fixturemonkey.kotlin.giveMeKotlinBuilder
import com.navercorp.fixturemonkey.kotlin.giveMeOne
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kr.co.vacgom.api.baby.application.BabyQueryService
import kr.co.vacgom.api.baby.domain.Baby
import kr.co.vacgom.api.baby.exceptioin.BabyError
import kr.co.vacgom.api.carehistoryitem.presentation.dto.*
import kr.co.vacgom.api.carehistoryitem.repository.CareHistoryItemRepository
import kr.co.vacgom.api.global.exception.error.BusinessException
import java.time.LocalDateTime

class CareHistoryItemCommandServiceTest: DescribeSpec({
    val fixtureMonkey = FixtureMonkey.builder().plugin(KotlinPlugin()).build()
    val careHistoryItemRepositoryMock: CareHistoryItemRepository = mockk(relaxed = true)
    val babyQueryServiceMock: BabyQueryService = mockk(relaxed = true)
    val sut = CareHistoryItemCommandService(
        careHistoryItemRepository = careHistoryItemRepositoryMock,
        babyQueryService = babyQueryServiceMock,
    )

    describe("육아 기록 아이템 생성 테스트") {
        context("정상적인 아이템 정보가 주어지는 경우") {
            val baby: Baby = fixtureMonkey.giveMeOne()
            val endTime = LocalDateTime.now()
            val startTime = endTime.minusMinutes(20)

            it("모유 수유 아이템 등록이 정상적으로 이루어진다.") {
                val request: BreastFeedingDto.Request = fixtureMonkey.giveMeKotlinBuilder<BreastFeedingDto.Request>()
                    .setExp(BreastFeedingDto.Request::babyId, baby.id)
                    .setExp(BreastFeedingDto.Request::leftStartDate, startTime)
                    .setExp(BreastFeedingDto.Request::leftEndDate, endTime)
                    .setExp(BreastFeedingDto.Request::rightStartDate, startTime)
                    .setExp(BreastFeedingDto.Request::rightEndDate, endTime)
                    .sample()

                shouldNotThrowAny { sut.addBreastFeeding(request) }
            }

            it("이유식 아이템 등록이 정상적으로 이루어진다.") {
                val request: BabyFoodDto.Request = fixtureMonkey.giveMeKotlinBuilder<BabyFoodDto.Request>()
                    .setExp(BabyFoodDto.Request::babyId, baby.id)
                    .sample()

                shouldNotThrowAny { sut.addBabyFood(request) }
            }

            it("목욕 아이템 등록이 정상적으로 이루어진다.") {
                val request: BathDto.Request = fixtureMonkey.giveMeKotlinBuilder<BathDto.Request>()
                    .setExp(BathDto.Request::babyId, baby.id)
                    .setExp(BathDto.Request::startDate, startTime)
                    .setExp(BathDto.Request::endDate, endTime)
                    .sample()

                shouldNotThrowAny { sut.addBath(request) }
            }

            it("유축 아이템 등록이 정상적으로 이루어진다.") {
                val request: BreastPumpingDto.Request = fixtureMonkey.giveMeKotlinBuilder<BreastPumpingDto.Request>()
                    .setExp(BreastPumpingDto.Request::babyId, baby.id)
                    .sample()

                shouldNotThrowAny { sut.addBreastPumping(request) }
            }

            it("기저귀 아이템 등록이 정상적으로 이루어진다.") {
                val request: DiaperDto.Request = fixtureMonkey.giveMeKotlinBuilder<DiaperDto.Request>()
                    .setExp(DiaperDto.Request::babyId, baby.id)
                    .sample()

                shouldNotThrowAny { sut.addDiaper(request) }
            }

            it("건강 아이템 등록이 정상적으로 이루어진다.") {
                val request: HealthDto.Request = fixtureMonkey.giveMeKotlinBuilder<HealthDto.Request>()
                    .setExp(HealthDto.Request::babyId, baby.id)
                    .sample()

                shouldNotThrowAny { sut.addHealth(request) }
            }

            it("분유 아이템 등록이 정상적으로 이루어진다.") {
                val request: BabyFormulaDto.Request = fixtureMonkey.giveMeKotlinBuilder<BabyFormulaDto.Request>()
                    .setExp(BabyFormulaDto.Request::babyId, baby.id)
                    .sample()

                every { babyQueryServiceMock.getBabyById(baby.id) } returns baby

                shouldNotThrowAny { sut.addBabyFormula(request) }
            }

            it("수면 아이템 등록이 정상적으로 이루어진다.") {
                val request: SleepDto.Request = fixtureMonkey.giveMeKotlinBuilder<SleepDto.Request>()
                    .setExp(SleepDto.Request::babyId, baby.id)
                    .setExp(SleepDto.Request::startDate, startTime)
                    .setExp(SleepDto.Request::endDate, endTime)
                    .sample()

                shouldNotThrowAny { sut.addSleep(request) }
            }

            it("간식 아이템 등록이 정상적으로 이루어진다.") {
                val request: SnackDto.Request = fixtureMonkey.giveMeKotlinBuilder<SnackDto.Request>()
                    .setExp(SnackDto.Request::babyId, baby.id)
                    .sample()

                shouldNotThrowAny { sut.addSnack(request) }
            }
        }

        context("비 정상적인 아이템 정보가 주어지는 경우") {
            val baby: Baby = fixtureMonkey.giveMeOne()
            val endTime = LocalDateTime.now()
            val startTime = endTime.minusMinutes(20)

            context("아기 ID가 존재하지 않는 경우") {
                it("${BabyError.BABY_NOT_FOUND} 예외가 발생한다.") {
                    val request: BreastFeedingDto.Request = fixtureMonkey.giveMeKotlinBuilder<BreastFeedingDto.Request>()
                        .setExp(BreastFeedingDto.Request::babyId, baby.id)
                        .setExp(BreastFeedingDto.Request::leftStartDate, startTime)
                        .setExp(BreastFeedingDto.Request::leftEndDate, endTime)
                        .setExp(BreastFeedingDto.Request::rightStartDate, startTime)
                        .setExp(BreastFeedingDto.Request::rightEndDate, endTime)
                        .sample()

                    every { babyQueryServiceMock.getBabyById(baby.id) } throws BusinessException(BabyError.BABY_NOT_FOUND)

                    val result = shouldThrow<BusinessException>{ sut.addBreastFeeding(request) }

                    result.errorCode shouldBe BabyError.BABY_NOT_FOUND
                    result.message shouldBe BabyError.BABY_NOT_FOUND.message
                }

                it("[이유식] ${BabyError.BABY_NOT_FOUND} 예외가 발생한다.") {
                    val request: BabyFoodDto.Request = fixtureMonkey.giveMeKotlinBuilder<BabyFoodDto.Request>()
                        .setExp(BabyFoodDto.Request::babyId, baby.id)
                        .sample()

                    every { babyQueryServiceMock.getBabyById(baby.id) } throws BusinessException(BabyError.BABY_NOT_FOUND)

                    val result = shouldThrow<BusinessException>{ sut.addBabyFood(request) }

                    result.errorCode shouldBe BabyError.BABY_NOT_FOUND
                    result.message shouldBe BabyError.BABY_NOT_FOUND.message
                }

                it("[목욕] ${BabyError.BABY_NOT_FOUND} 예외가 발생한다.") {
                    val request: BathDto.Request = fixtureMonkey.giveMeKotlinBuilder<BathDto.Request>()
                        .setExp(BathDto.Request::babyId, baby.id)
                        .setExp(BathDto.Request::startDate, startTime)
                        .setExp(BathDto.Request::endDate, endTime)
                        .sample()

                    every { babyQueryServiceMock.getBabyById(baby.id) } throws BusinessException(BabyError.BABY_NOT_FOUND)

                    val result = shouldThrow<BusinessException>{ sut.addBath(request) }

                    result.errorCode shouldBe BabyError.BABY_NOT_FOUND
                    result.message shouldBe BabyError.BABY_NOT_FOUND.message
                }

                it("[유축] ${BabyError.BABY_NOT_FOUND} 예외가 발생한다.") {
                    val request: BreastPumpingDto.Request = fixtureMonkey.giveMeKotlinBuilder<BreastPumpingDto.Request>()
                        .setExp(BreastPumpingDto.Request::babyId, baby.id)
                        .sample()

                    every { babyQueryServiceMock.getBabyById(baby.id) } throws BusinessException(BabyError.BABY_NOT_FOUND)

                    val result = shouldThrow<BusinessException>{ sut.addBreastPumping(request) }

                    result.errorCode shouldBe BabyError.BABY_NOT_FOUND
                    result.message shouldBe BabyError.BABY_NOT_FOUND.message
                }

                it("[기저귀] ${BabyError.BABY_NOT_FOUND} 예외가 발생한다.") {
                    val request: DiaperDto.Request = fixtureMonkey.giveMeKotlinBuilder<DiaperDto.Request>()
                        .setExp(DiaperDto.Request::babyId, baby.id)
                        .sample()

                    every { babyQueryServiceMock.getBabyById(baby.id) } throws BusinessException(BabyError.BABY_NOT_FOUND)

                    val result = shouldThrow<BusinessException>{ sut.addDiaper(request) }

                    result.errorCode shouldBe BabyError.BABY_NOT_FOUND
                    result.message shouldBe BabyError.BABY_NOT_FOUND.message
                }

                it("[건강] ${BabyError.BABY_NOT_FOUND} 예외가 발생한다.") {
                    val request: HealthDto.Request = fixtureMonkey.giveMeKotlinBuilder<HealthDto.Request>()
                        .setExp(HealthDto.Request::babyId, baby.id)
                        .sample()

                    every { babyQueryServiceMock.getBabyById(baby.id) } throws BusinessException(BabyError.BABY_NOT_FOUND)

                    val result = shouldThrow<BusinessException>{ sut.addHealth(request) }

                    result.errorCode shouldBe BabyError.BABY_NOT_FOUND
                    result.message shouldBe BabyError.BABY_NOT_FOUND.message
                }

                it("[분유] ${BabyError.BABY_NOT_FOUND} 예외가 발생한다.") {
                    val request: BabyFormulaDto.Request = fixtureMonkey.giveMeKotlinBuilder<BabyFormulaDto.Request>()
                        .setExp(BabyFormulaDto.Request::babyId, baby.id)
                        .sample()

                    every { babyQueryServiceMock.getBabyById(baby.id) } throws BusinessException(BabyError.BABY_NOT_FOUND)

                    val result = shouldThrow<BusinessException>{ sut.addBabyFormula(request) }

                    result.errorCode shouldBe BabyError.BABY_NOT_FOUND
                    result.message shouldBe BabyError.BABY_NOT_FOUND.message
                }

                it("[수면] ${BabyError.BABY_NOT_FOUND} 예외가 발생한다.") {
                    val request: SleepDto.Request = fixtureMonkey.giveMeKotlinBuilder<SleepDto.Request>()
                        .setExp(SleepDto.Request::babyId, baby.id)
                        .setExp(SleepDto.Request::startDate, startTime)
                        .setExp(SleepDto.Request::endDate, endTime)
                        .sample()

                    every { babyQueryServiceMock.getBabyById(baby.id) } throws BusinessException(BabyError.BABY_NOT_FOUND)

                    val result = shouldThrow<BusinessException>{ sut.addSleep(request) }

                    result.errorCode shouldBe BabyError.BABY_NOT_FOUND
                    result.message shouldBe BabyError.BABY_NOT_FOUND.message
                }

                it("[간식] ${BabyError.BABY_NOT_FOUND} 예외가 발생한다.") {
                    val request: SnackDto.Request = fixtureMonkey.giveMeKotlinBuilder<SnackDto.Request>()
                        .setExp(SnackDto.Request::babyId, baby.id)
                        .sample()

                    every { babyQueryServiceMock.getBabyById(baby.id) } throws BusinessException(BabyError.BABY_NOT_FOUND)

                    val result = shouldThrow<BusinessException>{ sut.addSnack(request) }

                    result.errorCode shouldBe BabyError.BABY_NOT_FOUND
                    result.message shouldBe BabyError.BABY_NOT_FOUND.message
                }
            }

            context("기록 종료 시간이 시작 시간보다 앞서는 경우") {
                it("[모유 수유] ${IllegalArgumentException()}가 발생한다.") {
                    val request: BreastFeedingDto.Request = fixtureMonkey.giveMeKotlinBuilder<BreastFeedingDto.Request>()
                        .setExp(BreastFeedingDto.Request::babyId, baby.id)
                        .setExp(BreastFeedingDto.Request::leftStartDate, endTime)
                        .setExp(BreastFeedingDto.Request::leftEndDate, startTime)
                        .setExp(BreastFeedingDto.Request::rightStartDate, endTime)
                        .setExp(BreastFeedingDto.Request::rightEndDate, startTime)
                        .sample()

                    every { babyQueryServiceMock.getBabyById(baby.id) } returns baby

                    shouldThrow<IllegalArgumentException>{ sut.addBreastFeeding(request) }
                }

                it("[목욕] ${IllegalArgumentException()}가 발생한다.") {
                    val request: BathDto.Request = fixtureMonkey.giveMeKotlinBuilder<BathDto.Request>()
                        .setExp(BathDto.Request::babyId, baby.id)
                        .setExp(BathDto.Request::startDate, endTime)
                        .setExp(BathDto.Request::endDate, startTime)
                        .sample()

                    every { babyQueryServiceMock.getBabyById(baby.id) } returns baby

                    shouldThrow<IllegalArgumentException>{ sut.addBath(request) }
                }

                it("[수면] ${IllegalArgumentException()}가 발생한다.") {
                    val request: SleepDto.Request = fixtureMonkey.giveMeKotlinBuilder<SleepDto.Request>()
                        .setExp(SleepDto.Request::babyId, baby.id)
                        .setExp(SleepDto.Request::startDate, endTime)
                        .setExp(SleepDto.Request::endDate, startTime)
                        .sample()

                    every { babyQueryServiceMock.getBabyById(baby.id) } returns baby

                    shouldThrow<IllegalArgumentException>{ sut.addSleep(request) }
                }
            }
        }
    }
})
