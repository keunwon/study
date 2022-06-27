package com.ch07

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

internal class PointTest : DescribeSpec({

    describe("plus") {
        it("'+' 연산자를 사용하여 Point x, y 값을 더한 객체를 반환합니다") {
            (point1 + point2) shouldBe Point(40, 60)
        }

        it("'+=' 연산자를 사용하여 Point x, y 값을 더한 객체를 반환합니다") {
            var point = Point(10, 10)
            point += point1

            point shouldBe Point(20, 30)
        }
    }

    describe("times") {
        it("입력된 값과 기존의 x, y 값을 곱한 객체를 반환합니다") {
            (point1 * 2.0) shouldBe Point(20, 40)
            (point1 * 1.5) shouldBe Point(15, 30)
        }
    }

    describe("unaryMinus") {
        it("x, y 값이 음수로 변경되어 객체를 반환합니다") {
            (-point1) shouldBe Point(-10, -20)
            (-point2) shouldBe Point(-30, -40)
        }
    }

    describe("get") {
        it("[0 ~ 1] 범위의 인덱스의 값을 입력하면 x or y 값을 반환합니다") {
            point1[0] shouldBe 10
            point1[1] shouldBe 20
        }

        it("[0 ~ 1] 범위 이외의 인덱스를 값을 입력하면 IndexOutOfBoundsException 발생합니다") {
            listOf(-1, 2, 3, 4, 5).forAll { index ->
                shouldThrowExactly<IndexOutOfBoundsException> {
                    point1[index]
                }.message shouldBe "Invalid coordinate $index"
            }
        }
    }
}) {
    companion object {
        val point1 = Point(10, 20)
        val point2 = Point(30, 40)
    }
}
