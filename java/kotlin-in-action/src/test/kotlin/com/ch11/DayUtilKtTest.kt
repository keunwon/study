package com.ch11

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import io.mockk.mockkStatic
import java.time.LocalDate
import java.time.LocalDateTime

internal class DayUtilKtTest : DescribeSpec({

    mockkStatic(LocalDateTime::class)


    describe("ago") {
        it("현재 날짜를 기준으로 [n]일 전 날짜를 반환합니다") {
            listOf(1, 2, 3, 4, 5).forAll { minusDay ->
                val date = minusDay.days.ago

                date shouldBe LocalDate.now().minusDays(minusDay.toLong())
            }
        }

        it("현재 날짜를 기준으로 [n]일 후 날짜를 반환합니다") {
            listOf(1, 2, 3, 4, 5).forAll { plusDay ->
                val date = plusDay.days.fromNow

                date shouldBe LocalDate.now().plusDays(plusDay.toLong())
            }
        }
    }
})
