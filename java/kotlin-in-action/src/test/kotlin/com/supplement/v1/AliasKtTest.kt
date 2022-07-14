package com.supplement.v1

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.maps.shouldHaveSize
import io.kotest.matchers.shouldBe
import java.io.ByteArrayOutputStream
import java.io.PrintStream

internal class AliasKtTest : DescribeSpec({

    beforeEach {
        outputStream.reset()
        System.setOut(PrintStream(outputStream))
    }

    afterEach {
        System.setOut(output)
    }

    describe("addHandler") {
        context("[type alias] 타입을 사용") {
            it("DSL 사용, 파라미터 값들을 출력합니다") {
                addHandler { num, letter, obj -> println("$num, $letter, $obj") }

                outputStream.toString() shouldBe "1, 2, 3\n"
            }

            it("로컬 함수를 사용, 파라미터 값들을 출력합니다") {
                val myHandler: (Int, String, Any) -> Unit = { num, letter, obj -> println("$num, $letter, $obj") }
                addHandler(myHandler)

                outputStream.toString() shouldBe "1, 2, 3\n"
            }
        }
    }

    describe("printArgs") {
        context("[type alias]을 사용한 Args 타입을 사용하여") {
            it("[test-1] 파라미터 값들을 출력합니다") {
                val arr: Args = arrayOf("apple", "banana", "cat")
                printArgs(arr)

                outputStream.toString() shouldBe "apple, banana, cat\n"
            }

            it("[test-2] 파라미터 값들을 출력합니다") {
                printArgs(arrayOf("apple, banana", "cat"))

                outputStream.toString() shouldBe "apple, banana, cat\n"
            }
        }
    }

    describe("StringKeyMap<V>") {
        it("key-value 값들을 반환합니다") {
            val map: StringKeyMap<Int> = mapOf("One" to 1, "Two" to 2)

            with (map) {
                shouldHaveSize(2)
                get("One") shouldBe 1
                get("Two") shouldBe 2
            }
        }
    }

}) {
    companion object {
        val outputStream = ByteArrayOutputStream()
        val output = System.out!!
    }
}
