package com.ch09

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

internal class ValidatorsTest : DescribeSpec({

    beforeContainer {
        println("beforeContainer")
        Validators.registerValidator(String::class, DefaultStringValidator)
        Validators.registerValidator(Int::class, DefaultIntValidator)
    }

    afterContainer {
        println("afterContainer")
    }

    describe("입력한 값을 검증합니다") {
        it("문자열을 검증합니다") {
            Validators[String::class].validate("문자열") shouldBe true
            Validators[String::class].validate("") shouldBe false

        }

        it("숫자를 검증합니다") {
            Validators[Int::class].validate(1) shouldBe true
            Validators[Int::class].validate(0) shouldBe true
            Validators[Int::class].validate(-1) shouldBe false
            Validators[Int::class].validate(-2) shouldBe false
        }
    }
})
