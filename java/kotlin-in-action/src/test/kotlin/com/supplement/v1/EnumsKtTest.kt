package com.supplement.v1

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

internal class EnumsKtTest : DescribeSpec({

    describe("mkString") {
        context("DAYSOFWEEK 제너릭 타입을 입력을 하면") {
            it("enum 모든 값을 반환합니다") {
                val enumToString = mkString<DAYSOFWEEK>()

                enumToString shouldBe """
                    MON, TUE, WED, THR, FRI, SAT, SUN, 
                """.trimIndent()
            }
        }
    }

    describe("enumFrom") {
        context("DAYSOFWEEK 반환할 enum 이름을 입력하면") {
            it("동일한 프로퍼티 이름의 enum 반환합니다") {
                days.forAll { (index, day) ->
                    val dayEnum = enumFrom<DAYSOFWEEK>(day)

                    dayEnum shouldBe DAYSOFWEEK.values()[index]
                }
            }

            it("동일한 이름이지만, 소문자인 경우 IllegalArgumentException 발생합니다") {
                days.forAll { (_, day) ->
                    shouldThrowExactly<IllegalArgumentException> {
                        enumFrom<DAYSOFWEEK>(day.lowercase())
                    }
                }
            }

            it("동일한 이름이 존재하지 않으면 IllegalArgumentException 발생합니다") {
                shouldThrowExactly<IllegalArgumentException> {
                    enumFrom<DAYSOFWEEK>("NONE")
                }
            }
        }
    }
}) {
    companion object {
        val days = listOf(
            0 to "MON",
            1 to "TUE",
            2 to "WED",
            3 to "THR",
            4 to "FRI",
            5 to "SAT",
            6 to "SUN"
        )
    }
}
