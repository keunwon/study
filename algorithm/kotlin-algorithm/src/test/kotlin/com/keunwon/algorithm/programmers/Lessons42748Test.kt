package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons42748Test : StringSpec({
    "case" {
        val array = intArrayOf(1, 5, 2, 6, 3, 7, 4)
        val commands = arrayOf(
            intArrayOf(2, 5, 3),
            intArrayOf(4, 4, 1),
            intArrayOf(1, 7, 3),
        )

        val actual = Lessons42748().solution(array, commands)

        actual shouldBe intArrayOf(5, 6, 3)
    }
})
