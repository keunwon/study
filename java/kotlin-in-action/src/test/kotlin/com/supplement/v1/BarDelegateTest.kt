package com.supplement.v1

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.io.ByteArrayOutputStream
import java.io.PrintStream

internal class BarDelegateTest : DescribeSpec({

    beforeEach {
        outputStream.reset()
        System.setOut(PrintStream(outputStream))
    }

    afterEach {
        System.setOut(output)
    }

    describe("[getValue] Int 타입의 변수의 값을 위임하여 반환합니다") {
        it("현재 숫자, 프로퍼티 이름을 출력을 하고 100을 반환합니다") {
            val y: Int by barDelegate

            y shouldBe 100
            outputStream.toString() shouldBe """
                thisRef = null
                property.name = y
                
            """.trimIndent()
        }
    }
}) {
    companion object {
        val barDelegate = BarDelegate()

        val outputStream = ByteArrayOutputStream()
        val output = System.out!!
    }
}
