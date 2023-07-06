package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons43163Test : StringSpec({
    "case_01" {
        val begin = "hit"
        val target = "cog"
        val words = arrayOf("hot", "dot", "dog", "lot", "log", "cog")

        val actual = Lessons43163().solution(begin, target, words)

        actual shouldBe 4
    }

    "case_02" {
        val begin = "hit"
        val target = "cog"
        val words = arrayOf("hot", "dot", "dog", "lot", "log")

        val actual = Lessons43163().solution(begin, target, words)

        actual shouldBe 0
    }
})
