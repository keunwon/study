package com.ch05

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe

internal class BookCollectionApiTest : DescribeSpec({
    describe("컬렉션 함수형 API") {
        context("flatMap") {
            it("저자 이름을 하나의 리스트에 담아 반환합니다") {
                val authors = books.flatMap { it.authors }.toSet()

                authors.shouldContainExactlyInAnyOrder(
                    "Jasper Fforde", "Terry Pratchett", "Neil Gaiman")
            }

            it("string") {
                val strings = listOf("abc", "def")

                strings.flatMap { it.toList() } shouldBe "abcdef".toList()
            }
        }

        it("flatten") {
            val numbers = listOf(listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8, 9))

            numbers.flatten() shouldBe listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        }
    }
}) {
    companion object {
        val books = listOf(
            Book("Thursday Next", listOf("Jasper Fforde")),
            Book("Mort", listOf("Terry Pratchett")),
            Book("Good Omens", listOf("Terry Pratchett", "Neil Gaiman"))
        )
    }
}
