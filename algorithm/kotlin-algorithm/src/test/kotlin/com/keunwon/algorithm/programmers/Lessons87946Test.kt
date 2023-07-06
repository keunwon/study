package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons87946Test : StringSpec({
    "case" {
        val k = 80
        val dungeons = arrayOf(
            intArrayOf(80, 20),
            intArrayOf(50, 40),
            intArrayOf(30, 10),
        )

        val actual = Lessons87946().solution(k, dungeons)

        actual shouldBe 3
    }
})
