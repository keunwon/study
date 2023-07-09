package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons42627Test : StringSpec({
    "case_01" {
        val jobs = arrayOf(
            intArrayOf(0, 3),
            intArrayOf(1, 9),
            intArrayOf(2, 6),
        )

        val actual = Lessons42627().solution(jobs)

        actual shouldBe 9
    }
})
