package com.keunwon.algorithm.againresolve

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class ALessons87390Test : StringSpec({
    "기본 예제" {
        forAll(
            row(3, 2L, 5L, intArrayOf(3, 2, 2, 3)),
            row(4, 7L, 14L, intArrayOf(4, 3, 3, 3, 4, 4, 4, 4)),
        ) { n, left, right, result ->
            val actual = ALessons87390().solution(n, left, right)
            actual shouldBe result
        }
    }
})
