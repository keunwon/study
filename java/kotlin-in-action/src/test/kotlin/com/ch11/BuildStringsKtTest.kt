package com.ch11

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

internal class BuildStringsKtTest : DescribeSpec({

    describe("문자열을 반환합니다") {
        it("buildString") {
            val result = buildString {
                it.append("Hi, ")
                it.append("Hello")
            }
            result shouldBe "Hi, Hello"
        }
        
        it("buildString2") {
            val result = buildString2 {
                append("Hi, ")
                append("Hello")
            }
            result shouldBe "Hi, Hello"
        }

        it("buildString3") {
            val string = buildString3 {
                append("Hi, ")
                append("Hello")
            }

            string shouldBe "Hi, Hello"
        }

        context("appendExcl") {
            it("test-1") {
                val sb = StringBuilder("Hi, Hello")
                sb.appendExcl()

                sb.toString() shouldBe "Hi, Hello!"
            }

            it("test-2") {
                val result = buildString2(appendExcl)
                result shouldBe "!"
            }
        }

        context("appendExcl2") {
            it("'test-1!' 출력합니다") {
                val sb = StringBuilder()
                sb.append("test-1")
                sb.appendExcl2()

                sb.toString() shouldBe "test-1!"
            }
        }
    }
})
