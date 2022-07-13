package com.ch09

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

internal class ValidatorsTest : DescribeSpec({

    beforeContainer {
        println("beforeContainer")

        with (Validators) {
            registerValidator(String::class, DefaultStringValidator)
            registerValidator(Int::class, DefaultIntValidator)
        }
    }

    afterContainer {
        println("afterContainer")
    }

    describe("입력한 값을 검증합니다") {
        it("문자열을 검증합니다") {
            with (Validators[String::class]) {
                validate("문자열") shouldBe true
                validate("") shouldBe false
            }
        }

        it("숫자를 검증합니다") {
            with (Validators[Int::class]) {
                validate(1) shouldBe true
                validate(0) shouldBe true
                validate(-1) shouldBe false
                validate(-2) shouldBe false
            }
        }
    }
})
