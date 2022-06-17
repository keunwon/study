package com.ch05

import com.ch05.java.View
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import java.io.ByteArrayOutputStream
import java.io.PrintStream

internal class OnclickListenerTest : DescribeSpec({

    beforeEach() {
        System.setOut(PrintStream(outputStream))
        outputStream.reset()
    }

    afterEach {
        System.setOut(output)
    }

    describe("listener") {
        context("문자열을 입력하면 대응하는 문자열을 반환합니다") {
            listOf(
                "first" to "First button",
                "second" to "Second button",
                "unknown" to "Unknown button"
            ).forAll { (id, result) ->
                it("id: $id -> result: $result") {
                    listenerOnclick.onclick(View(id))

                    outputStream.toString() shouldBe "$result\n"
                }
            }
        }
    }
}) {
    companion object {
        val outputStream = ByteArrayOutputStream()
        val output = System.out!!
    }
}
