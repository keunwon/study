package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons142086Test : StringSpec({
    "case" {
        forAll(
            row("banana", intArrayOf(-1, -1, -1, 2, 2, 2)),
            row("foobar", intArrayOf(-1, -1, 1, -1, -1, -1)),
        ) { s, result ->
            val actual = Lessons142086().solution(s)
            actual shouldBe result
        }
    }
})
