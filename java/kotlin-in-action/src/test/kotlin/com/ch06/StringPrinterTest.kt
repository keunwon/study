package com.ch06

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.io.ByteArrayOutputStream
import java.io.PrintStream

internal class StringPrinterTest : DescribeSpec({

    beforeEach {
        outputStream.reset()
        System.setOut(PrintStream(outputStream))
    }

    afterEach {
        System.setOut(output)
    }

    describe("process") {
        context("StringPrinter 구현") {
            it("입력된 문자열을 출력합니다") {
                val text = "process"
                StringPrinter().process(text)

                outputStream.toString() shouldBe "$text\n"
            }
        }

        context("NullableStringPrinter 구현") {
            it("입력된 문자열을 출력합니다") {
                val text = "process"
                NullableStringPrinter().process(text)

                outputStream.toString() shouldBe "$text\n"
            }

            it("null 입력 시 아무것도 출력하지 않습니다") {
                NullableStringPrinter().process(null)

                outputStream.toString() shouldBe ""
            }
        }
    }
}) {
    companion object {
        val outputStream = ByteArrayOutputStream()
        val output = System.out!!
    }
}
