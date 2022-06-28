package com.ch07

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.time.LocalDate

internal class LocalDateKtTest : DescribeSpec({

    beforeEach {
        outputStream.reset()
        System.setOut(PrintStream(outputStream))
    }

    afterEach {
        System.setOut(output)
    }

    describe("iterator") {
        it("반복문을 이용하여 날짜 정보를 출력합니다") {
            printIteratorByLocalDate()

            outputStream.toString() shouldBe """
                ${LocalDate.of(2016, 12, 31)}
                ${LocalDate.of(2017, 1, 1)}
                
            """.trimIndent()
        }
    }
}) {
    companion object {
        val outputStream = ByteArrayOutputStream()
        val output = System.out
    }
}
