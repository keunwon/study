package com.ch07

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeTrue

internal class RectangleTest : DescribeSpec({

    describe("contains") {
        it("x, y의 값이 범위 내에 포함되어 있는 경우 true 반환합니다") {
            rectangle.contains(Point(15, 15)).shouldBeTrue()
        }

        it("x, y의 값이 범위 내에 포함되어 있지 않으면 false 반환합니다") {
            rectangle.contains(Point(10, 10)).shouldBeTrue()
            rectangle.contains(Point(19, 19)).shouldBeTrue()
        }
    }
}) {
    companion object {
        val rectangle = Rectangle(Point(10, 10), Point(20, 20))
    }
}
