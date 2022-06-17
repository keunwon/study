package com.ch02

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class ExprTest : DescribeSpec({

    describe("계산하기") {
        it("덧셈 연산") {
            forAll(
                row(1, 2, 4, 7)
            ) { num1, num2, num3, sum ->
                eval(Sum(Sum(Num(num1), Num(num2)), Num(num3))) shouldBe sum
            }
        }
    }
})
