package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons118667Test : StringSpec({
    "case" {
        forAll(
            row(intArrayOf(3, 2, 7, 2), intArrayOf(4, 6, 5, 1), 2),
            row(intArrayOf(1, 2, 1, 2), intArrayOf(1, 10, 1, 2), 7),
            row(intArrayOf(1, 1), intArrayOf(1, 5), -1),
        ) { queue1, queue2, result ->
            val actual = Lessons118667().solution(queue1, queue2)
            actual shouldBe result
        }
    }
})
