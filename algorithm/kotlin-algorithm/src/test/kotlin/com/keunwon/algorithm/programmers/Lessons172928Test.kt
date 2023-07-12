package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons172928Test : StringSpec({
    "case" {
        forAll(
            row(arrayOf("SOO", "OOO", "OOO"), arrayOf("E 2", "S 2", "W 1"), intArrayOf(2, 1)),
            row(arrayOf("SOO", "OXX", "OOO"), arrayOf("E 2", "S 2", "W 1"), intArrayOf(0, 1)),
            row(arrayOf("OSO", "OOO", "OXO", "OOO"), arrayOf("E 2", "S 3", "W 1"), intArrayOf(0, 0)),
        ) { park, routes, result ->
            val actual = Lessons172928().solution(park, routes)
            actual shouldBe result
        }
    }
})
