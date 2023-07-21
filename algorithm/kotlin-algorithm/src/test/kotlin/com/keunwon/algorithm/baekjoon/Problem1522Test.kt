package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Problem1522Test : StringSpec({
    "case" {
        forAll(
            row("abababababababa", 3),
            row("ba", 0),
            row("aaaabbbbba", 0),
            row("abab", 1),
            row("aabbaaabaaba", 2),
            row("aaaa", 0),
        ) { word, result ->
            val actual = Problem1522().solution(word)
            actual shouldBe result
        }
    }
})
