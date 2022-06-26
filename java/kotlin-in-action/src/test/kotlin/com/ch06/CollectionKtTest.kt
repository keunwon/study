package com.ch06

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.io.StringReader

internal class CollectionKtTest : DescribeSpec({

    beforeEach {
        outputStream.reset()
        System.setOut(PrintStream(outputStream))
    }

    afterEach {
        System.setOut(output)
    }

    describe("readNumbers") {
        context("BufferedReader 입력하면") {
            it("행을 기준으로 나누어 리스트를 반환합니다") {
                val text = """
                    1
                    22
                    
                    4444
                    55555
                """.trimIndent()
                val br = BufferedReader(StringReader(text))

                readNumbers(br) shouldContainExactly listOf(1, 22, null, 4444, 55555)
            }
        }
    }

    describe("addValidNumbers") {
        context("null 포함한 숫자 리스트를 입력하면") {
            it("리스트 숫자의 합, null 개수를 출력합니다") {
                addValidNumbers(listOf(1, 2, null, 3, 4, null, 5))

                outputStream.toString() shouldBe """
                    Sum of valid numbers: 15
                    Invalid numbers: 2
                    
                """.trimIndent()
            }
        }
    }

    describe("copyElements") {
        it("읽기 전용과 변경 가능한 컬렉션") {
            val source: Collection<Int> = arrayListOf(3, 5, 7)
            val target: MutableCollection<Int> = arrayListOf(1)

            copyElements(source, target)

            target shouldContainExactly arrayListOf(1, 3, 5, 7)
        }

        xit("컴파일 오류 발생") {
            val source: Collection<Int> = arrayListOf(3, 5, 7)
            val target: Collection<Int> = arrayListOf(1)

            //copyElements(source, target) - compile error
        }
    }

    describe("printInUppercase") {
        context("읽기 전용 리스트를 입력하는 경우") {
            it("내부 구현에 자바 유틸 메서드를 사용하면 리스트를 변경할 수 있다") {
                val list = listOf("first", "second", "third", "fourth", "fifth")

                printInUppercase(list)

                outputStream.toString() shouldBe """
                    ${list.map { it.uppercase() }}
                    ${list[0].uppercase()}
                    
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
