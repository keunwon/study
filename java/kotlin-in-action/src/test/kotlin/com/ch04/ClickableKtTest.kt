package com.ch04

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.io.ByteArrayOutputStream
import java.io.PrintStream

internal class ClickableKtTest : DescribeSpec({
    val output = ByteArrayOutputStream()
    val out = System.out

    describe("showOff") {
        beforeAny {
            output.reset()
            System.setOut(PrintStream(output))
        }

        afterAny {
            System.setOut(out)
        }

        context("Clickable, Focusable 동시 구현 시") {
            it("디폴트 메서드 실행") {
                val button = Button()
                button.showOff()

                output.toString() shouldBe """
                    I'm clickable!
                    I'm focusable!
                    
                """.trimIndent()
            }
        }
    }
})