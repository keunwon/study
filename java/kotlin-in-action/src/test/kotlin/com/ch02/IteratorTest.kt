package com.ch02

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

internal class IteratorTest : DescribeSpec({

    describe("범위 원소 검사") {
        context("a ~ z, A ~ Z 범위 입력") {
            (('a'..'z') zip ('A'..'Z'))
                .forEach { (letter1: Char, letter2: Char) ->
                    it("소문자: $letter1, 대문자: $letter2") {
                        isLetter(letter1) shouldBe true
                        isLetter(letter2) shouldBe true
                    }
                }
        }

        context("숫자가 아닌 영문을 입력") {
            (('a'..'z') + ('A'..'Z'))
                .forEach { letter ->
                    it("문자 letter: $letter") {
                        isNotDigit(letter) shouldBe true
                    }
                }
        }

        context("특정 범위에 포함 여부에 따라 문자열 반환") {
            it("0 ~ 9 범위") {
                ('0'..'9').forEach { letter ->
                    it("문자: $letter") {
                        recognize(letter) shouldBe "It's as digit!"
                    }
                }
            }

            it("a ~ z, A ~ Z 범위") {
                (('a'..'z') + ('A'..'Z')).forEach { letter ->
                    it("문자: $letter") {
                        recognize(letter) shouldBe "It's a letter!"
                    }
                }
            }
        }
    }
})
