package com.supplement.v1

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.io.ByteArrayOutputStream
import java.io.PrintStream

internal class OthersKtTest : DescribeSpec({

    beforeEach {
        outputStream.reset()
        System.setOut(PrintStream(outputStream))
    }

    afterEach {
        System.setOut(output)
    }

    it("printOnEach") {
        printOnEach(listOf(1, 2, 3, 4, 5))

        outputStream.toString() shouldBe "1\n2\n3\n4\n5\n1,4,9,16,25\n"
    }

    it("takeIf") {
        val num = listOf(1, 2, 3, 4, 5).takeIf { it.size == 10 }

        num shouldBe null
    }
}) {
    companion object {
        val outputStream = ByteArrayOutputStream()
        val output = System.out!!
    }
}
