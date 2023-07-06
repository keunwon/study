package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons42628Test : StringSpec({
    "case" {
        forAll(
            row(
                arrayOf("I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"),
                intArrayOf(0, 0),
            ),
            row(
                arrayOf("I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"),
                intArrayOf(333, -45)
            ),
        ) { operations, result ->
            val actual = Lessons42628().solution(operations)
            actual shouldBe result
        }
    }
})
