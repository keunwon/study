package com.ch07.eqquals

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue

internal class PointEqualsTest : DescribeSpec({

    describe("equals") {
        it("값이 동일한 Point 객체를 비교하면 true 반환합니다") {
            (Point(10, 20) == Point(10, 20)).shouldBeTrue()
        }

        it("값이 다른 Point 객체를 비교하면 false 반환합니다") {
            (Point(10, 20) == Point(10, 30)).shouldBeFalse()
        }

        it("Point == Null 비교하면 false 반환합니다") {
            (null == Point(10, 20)).shouldBeFalse()
        }
    }
})
