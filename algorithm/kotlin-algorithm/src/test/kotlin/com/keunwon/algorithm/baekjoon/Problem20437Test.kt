package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Problem20437Test : StringSpec({
    "case" {
        forAll(
            row("superaquatornado", 2, intArrayOf(4, 8)),
            row("abcdefghijklmnopqrstuvwxyz", 5, intArrayOf(-1)),
            row("abaaaba", 3, intArrayOf(3, 4)),
        ) { w, k, result ->
            val actual = Problem20437().solution(w, k)
            actual shouldBe result
        }
    }
})
