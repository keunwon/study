package com.keunwon.algorithm.againresolve

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class ALessons64065Test : StringSpec({
    "기본 예제" {
        forAll(
            row("{{2},{2,1},{2,1,3},{2,1,3,4}}", intArrayOf(2, 1, 3, 4)),
            row("{{1,2,3},{2,1},{1,2,4,3},{2}}", intArrayOf(2, 1, 3, 4)),
            row("{{20,111},{111}}", intArrayOf(111, 20)),
            row("{{123}}", intArrayOf(123)),
            row("{{4,2,3},{3},{2,3,4,1},{2,3}}", intArrayOf(3, 2, 4, 1)),
        ) { s, result ->
            val actual = ALessons64065().solution(s)
            actual shouldBe result
        }
    }
})
