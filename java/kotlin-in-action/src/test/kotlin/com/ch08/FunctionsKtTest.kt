package com.ch08

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import java.io.ByteArrayOutputStream
import java.io.PrintStream

internal class FunctionsKtTest : DescribeSpec({

    beforeEach {
        outputStream.reset()
        System.setOut(PrintStream(outputStream))
    }

    afterEach {
        System.setOut(output)
    }

    describe("sum") {
        it("합계를 구합니다") {
            forAll(
                row(1, 1, 2),
                row(1, 2, 3),
                row(2, 3, 5),
                row(4, 5, 9)
            ) { num1, num2, result ->
                sum(num1, num2) shouldBe result
            }
        }
    }

    describe("action") {
        it("42 출력합니다") {
            action()

            outputStream.toString() shouldBe "42\n"
        }
    }

    describe("canReturnNull") {
        it("null 반환합니다") {
            canReturnNull(10, 20).shouldBeNull()
        }
    }

    describe("twoAndThree") {
        it("인자로 받은 함수를 호출하여 'The result is 5' 출력합니다") {
            twoAndThree { x, y -> x + y }

            outputStream.toString() shouldBe "The result is 5\n"
        }
    }

    describe("String.filter") {
        it("영문 소문자만 모아서 출력합니다") {
            val text = "ab1c"

            text.filter { it in 'a'..'z' } shouldBe "abc"
        }
    }

    describe("Collection.joinToString") {
        it("컬렉션 내용을 문자열로 변환하여 반환합니다") {
            val letters = listOf("Alpha", "Beta")

            with (letters) {
                joinToString() shouldBe "Alpha, Beta"
                joinToString { it.lowercase() } shouldBe "alpha, beta"
                joinToString(
                    separator = "! ",
                    postfix = "! ",
                    transform = { it.lowercase() }
                ) shouldBe "alpha! beta! "
            }
        }

        it("컬력션 내용을 null 변환하여 반환합니다") {
            val letters = listOf("Alpha", "Beta")

            letters.joinToString(transform = null) shouldBe "null, null"
        }
    }
}) {
    companion object {
        val outputStream = ByteArrayOutputStream()
        val output = System.out!!
    }
}
