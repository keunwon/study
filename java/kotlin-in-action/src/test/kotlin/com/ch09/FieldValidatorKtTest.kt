package com.ch09

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue

internal class FieldValidatorKtTest : DescribeSpec({
    describe("DefaultStringValidator") {
        context("문자열을 입력하는 경우") {
            it("비어있지 않으면 true 반환합니다") {
                Validators[String::class].validate("문자열").shouldBeTrue()
            }

            it("비어있는 경우 false 반환합니다") {
                Validators[String::class].validate("").shouldBeFalse()
            }
        }
    }

    describe("DefaultIntValidator") {
        context("숫자를 입력하는 경우") {
            it("0 이상의 숫자를 입력하면 true 반환합니다") {
                listOf(1, 2, 3, 4, 5).forAll { num ->
                    Validators[Int::class].validate(num).shouldBeTrue()
                }
            }

            it("0 미만의 숫자를 입력하면 false 반환합니다") {
                listOf(-5, -4, -3, -2, -1).forAll { num ->
                    Validators[Int::class].validate(num).shouldBeFalse()
                }
            }
        }
    }
}) {
    init {
        Validators.registerValidator(String::class, DefaultStringValidator)
        Validators.registerValidator(Int::class, DefaultIntValidator)
    }
}
