package com.ch02

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

internal class ExprTest : DescribeSpec({

    describe("계산하기") {
        context("덧셈 연산") {
            table(
                headers("숫자 1", "숫자 2", "숫자 3", "계산 결과"),
                row(1, 2, 4, 7)
            ).forAll { num1, num2, num3, result ->
                eval(Sum(Sum(Num(num1), Num(num2)), Num(num3))) shouldBe result
            }
        }
    }
})