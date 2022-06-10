package com.ch03

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

internal class JoinToStringTest : DescribeSpec({

    describe("collection 문자열로 변환") {

        table(
            headers("list collection", "result"),
            row(listOf(1, 2, 3), "(1; 2; 3)"),
            row(listOf(3, 4, 5), "(3; 4; 5)")
        ).forAll { list, result ->
            it("list: $list, 변환 값: $result") {
                joinToString(
                    collection = list,
                    separator = "; ",
                    prefix = "(",
                    postfix = ")") shouldBe result
            }
        }

        table(
            headers("list collection", "result"),
            row(listOf(1, 2, 3), "1, 2, 3"),
            row(listOf(3, 4, 5), "3, 4, 5")
        ).forAll { list, result ->
            it("list: $list, 변환 값: $result") {
                joinToString(list) shouldBe result
            }
        }

        listOf(
            arrayListOf(1, 2, 3)
        ).forAll { list ->
            it ("list: $list") {
                list.joinToString(" ") shouldBe "1 2 3"
            }
        }

        table(
            headers("list", "result"),
            row(listOf("one", "two", "eight"), "one two eight")
        ).forAll { list, result ->
            list.join(" ") shouldBe result
        }
    }
})