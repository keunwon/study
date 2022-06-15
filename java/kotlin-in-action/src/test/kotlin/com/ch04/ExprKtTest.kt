package com.ch04

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

internal class ExprKtTest : DescribeSpec({

    describe("eval") {
        context("하나의 숫자을 입력하면, 동일한 값의 숫자가 반환됩니다") {
            listOf(1, 2, 3, 4, 5).forAll { num ->
                eval(Expr.Num(num)) shouldBe num
            }
        }

        context("두개의 숫자를 입력하면, 입력한 합계를 반환됩니다") {
            forAll(
                row(1, 1, 2),
                row(2, 2, 4),
                row(3, 3, 6),
                row(4, 4, 8)
            ) { num1, num2, result  ->
                val sum = Expr.Sum(Expr.Num(num1), Expr.Num(num2))
                eval(sum) shouldBe result
            }
        }
    }
})