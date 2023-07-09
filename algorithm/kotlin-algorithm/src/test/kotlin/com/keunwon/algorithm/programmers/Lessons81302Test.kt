package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons81302Test : StringSpec({
    "case_01" {
        val places = arrayOf(
            arrayOf("POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"),
            arrayOf("POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"),
            arrayOf("PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"),
            arrayOf("OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"),
            arrayOf("PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"),
        )
        val actual = Lessons81302().solution(places)
        actual shouldBe intArrayOf(1, 0, 1, 1, 1)
    }
})
