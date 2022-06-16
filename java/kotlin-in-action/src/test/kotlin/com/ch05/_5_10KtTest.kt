package com.ch05

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.io.ByteArrayOutputStream
import java.io.PrintStream

internal class _5_10KtTest : DescribeSpec({
    beforeAny {
        outputStream.reset()
        System.setOut(PrintStream(outputStream))
    }

    afterAny {
        System.setOut(output)
    }

    describe("printMessageWithPrefix") {
        context("오류 내용을 포함한 리스트, 접두사를 입력하면") {
            it("오류 메시지를 출력합니다") {
                val errors = listOf("403 Forbidden", "404 Not Found")
                val prefix = "Error:"

                printMessageWithPrefix(errors, prefix)

                outputStream.toString() shouldBe """
                    Error: 403 Forbidden
                    Error: 404 Not Found
                    
                """.trimIndent()
            }
        }
    }

    describe("printProblemCounts") {
        context("오류 내용을 포함한 리스트를 입력하면") {
            it("4XX, 5XX로 시작하는 오류 내용의 개수를 출력합니다") {
                printProblemCounts(listOf("200 OK", "418 I'm a teapot", "500 Internal Server Error"))

                outputStream.toString() shouldBe """
                    1 client errors, 1 server errors
                    
                """.trimIndent()
            }
        }
    }
}) {
    companion object {
        val outputStream = ByteArrayOutputStream()
        val output = System.out!!
    }
}