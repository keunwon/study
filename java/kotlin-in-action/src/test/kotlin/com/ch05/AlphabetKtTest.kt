package com.ch05

import com.ch03.joinToString
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

internal class AlphabetKtTest : DescribeSpec({

    describe("alphabet<N>") {
        context("대문자 알파벳 A ~ Z 까지의 문자 리스트를 반환합니다") {
            it("alphabet") {
                alphabet() shouldBe resultText
            }

            it("alphabet2") {
                alphabet2() shouldBe resultText
            }

            it("alphabet3") {
                alphabet3() shouldBe resultText
            }

            it("alphabet4") {
                alphabet4() shouldBe resultText
            }

            it("alphabet5") {
                alphabet5() shouldBe resultText
            }
        }
    }
}) {
    companion object {
        val resultText = generateAlphabetToString() + "\nNow I know the alphabet!"

        private fun generateAlphabetToString() =
            List(26) { 'A' + it }.joinToString("")
    }
}
