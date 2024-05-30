package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson87946Test : StringSpec({
    "case-1" {
        val k = 80
        val dungeons = arrayOf(
            intArrayOf(80, 20),
            intArrayOf(50, 40),
            intArrayOf(30, 10)
        )

        val actual = Lesson87946().solution(k, dungeons)

        actual shouldBe 3
    }

    "case-2" {
        val k = 80
        val dungeons = arrayOf(
            intArrayOf(80, 20),
            intArrayOf(90, 40),
            intArrayOf(100, 10)
        )

        val actual = Lesson87946().solution(k, dungeons)

        actual shouldBe 1
    }
})
