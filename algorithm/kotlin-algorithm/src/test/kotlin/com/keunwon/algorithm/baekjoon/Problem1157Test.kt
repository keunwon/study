package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Problem1157Test : StringSpec({
    "case" {
        forAll(
            row("Mississipi", '?'),
            row("zZa", 'Z'),
            row("z", 'Z'),
            row("baaa", 'A'),
        ) { word, result ->
            val actual = Problem1157().solution(word)
            actual shouldBe result
        }
    }
})
