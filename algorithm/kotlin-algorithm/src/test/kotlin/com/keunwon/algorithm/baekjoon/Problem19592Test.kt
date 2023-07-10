package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Problem19592Test : StringSpec({
    "case_01" {
        forAll(
            row(12, 11, intArrayOf(3, 2, 1), 10),
            row(12, 9, intArrayOf(3, 2, 1), -1),
            row(12, 10, intArrayOf(3, 4, 5), 0),
            row(80, 80, intArrayOf(80, 60, 70), -1),
            row(80, 80, intArrayOf(70, 50, 60), 72),
        ) { x, y, speeds, result ->
            val actual = Problem19592().solution(x, y, speeds.map { it.toDouble() }.toDoubleArray())
            actual shouldBe result
        }
    }
})
