package com.supplement.v1

import com.supplement.printNumsWithNames
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.io.ByteArrayOutputStream
import java.io.PrintStream

internal class ZipsKtTest : DescribeSpec({

    beforeEach {
        outputStream.reset()
        System.setOut(PrintStream(outputStream))
    }

    afterEach {
        System.setOut(output)
    }

    describe("printNumsWithNames") {
        context("입력된 두개의 리스트의 크기가 동일한 경우") {
            it("서로 다른 리스트의 내용을 묶어서 출력합니다") {
                val nums = listOf(1, 2, 3)
                val names = listOf("One", "Two", "Three")

                printNumsWithNames(nums, names)

                outputStream.toString() shouldBe """
                    ${nums[0]} = ${names[0]}
                    ${nums[1]} = ${names[1]}
                    ${nums[2]} = ${names[2]}
                    
                """.trimIndent()
            }
        }

        context("입력된 두개의 리스트의 크기가 작은 경우") {
            it("크기가 작은 리스트를 기준으로, 서로 다른 리스트의 내용을 묶어서 출력합니다") {
                val nums = listOf(1, 2, 3)
                val names = listOf("One", "Two")

                printNumsWithNames(nums, names)

                outputStream.toString() shouldBe """
                    ${nums[0]} = ${names[0]}
                    ${nums[1]} = ${names[1]}
                    
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
