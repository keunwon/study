package com.ch06

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.io.ByteArrayOutputStream
import java.io.PrintStream

internal class ArrayKtKtTest : DescribeSpec({

    beforeEach {
        outputStream.reset()
        System.setOut(PrintStream(outputStream))
    }

    afterEach {
        System.setOut(output)
    }

    describe("printIndices") {
        it("입력된 배열의 값을 포함한 문자열을 출력합니다") {
            val arr = arrayOf("Bob", "Alice")

            printIndices(arr)

            outputStream.toString() shouldBe """
                Argument 0 is: ${arr[0]}
                Argument 1 is: ${arr[1]}
                
            """.trimIndent()
        }
    }

    describe("generateAlphabet") {
        it("a ~ z 범위의 문자열 배열을 반환합니다") {
            generateAlphabet() shouldBe alphabet
        }
    }

    describe("printFormat") {
        context("길이가 3인 문자열 리스트를 입력하면") {
            it("배열의 값을 '/' 구분자를 추가하여 문자열로 출력합니다") {
                printFormat(listOf("a", "b", "c"))

                outputStream.toString() shouldBe "a/b/c\n"
            }
        }
    }

    describe("printIntArray") {
        it("배열의 내용을 출력합니다") {
            printIntArray()

            outputStream.toString() shouldBe "1, 4, 9, 16, 25\n"
        }
    }

    describe("printForEachIndexed") {
        it("배열의 내용을 출력합니다") {
            val arr = arrayOf("Bob", "Alice")

            printForEachIndexed(arr)

            outputStream.toString() shouldBe """
                Argument 0 is: ${arr[0]}
                Argument 1 is: ${arr[1]}
                
            """.trimIndent()
        }
    }
}) {
    companion object {
        val outputStream = ByteArrayOutputStream()
        val output = System.out!!

        val alphabet = arrayOf(
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
            "n", "o", "p", "q", "r", "s", "t", "u", "v",  "w", "x", "y", "z"
        )
    }
}
