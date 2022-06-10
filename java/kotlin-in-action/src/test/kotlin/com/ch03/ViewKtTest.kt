package com.ch03

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.io.ByteArrayOutputStream
import java.io.PrintStream

internal class ViewKtTest : DescribeSpec({
    val output = ByteArrayOutputStream()
    val out = System.out

    describe("오버라이드") {
        beforeAny {
            output.reset()
            System.setOut(PrintStream(output))
        }

        afterAny {
            System.setOut(out)
        }

        context("View click") {
            View().click()
            output.toString() shouldBe "View clicked\n"
        }

        context("Button click") {
            Button().click()
            output.toString() shouldBe "Button clicked\n"
        }

        context("View showOff") {
            val view: View = View()
            view.showOff()
            output.toString() shouldBe "I'm a view!\n"
        }

        context("확장함수 View showOff") {
            val view: View = View()
            view.showOff()
            output.toString() shouldBe "I'm a view!\n"
        }

        context("확장함수 Button showOff") {
            val view: View = Button()
            view.showOff()
            output.toString() shouldBe "I'm a view!\n"
        }

        context("Button showOff") {
            Button().showOff()
            output.toString() shouldBe "I'm a button!\n"
        }
    }
})