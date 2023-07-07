package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Lessons160586Test : StringSpec({
    "case" {
        forAll(
            row(arrayOf("ABACD", "BCEFD"), arrayOf("ABCD", "AABB"), intArrayOf(9, 4)),
            row(arrayOf("AA"), arrayOf("B"), intArrayOf(-1)),
            row(arrayOf("AGZ", "BSSS"), arrayOf("ASA", "BGZ"), intArrayOf(4, 6)),
        ) { keymap, targets, result ->
            val actual = Lessons160586().solution(keymap, targets)
            actual shouldBe result
        }
    }
})
