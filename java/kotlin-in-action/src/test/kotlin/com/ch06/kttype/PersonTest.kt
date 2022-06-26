package com.ch06.kttype

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.nulls.shouldBeNull

internal class PersonTest : DescribeSpec({

    describe("findOlderThan") {
        context("나이가 null 이면") {
            it("null 반환됩니다") {
                personTo20Age.findOlderThan(Person("세종대왕", null)).shouldBeNull()
                personToNullAge.findOlderThan(Person("세종대왕", 200)).shouldBeNull()
            }
        }

        context("입력받은 나이를 기준으로 비교하면") {
            it("나이가 작으면 true 반환합니다") {
                personTo20Age.findOlderThan(personTo10Age)!!.shouldBeTrue()
            }

            it("나이가 많으면 false 반환합니다") {
                personTo10Age.findOlderThan(personTo20Age)!!.shouldBeFalse()
            }
        }
    }
}) {
    companion object {
        val personTo20Age = Person("홍길동", 20)
        val personTo10Age = Person("홍길동", 10)
        val personToNullAge = Person("홍길동", null)
    }
}
