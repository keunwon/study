package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons42890Test : StringSpec({
    "case_01" {
        val relation = arrayOf(
            arrayOf("100", "ryan", "music", "2"),
            arrayOf("200", "apeach", "math", "2"),
            arrayOf("300", "tube", "computer", "3"),
            arrayOf("400", "con", "computer", "4"),
            arrayOf("500", "muzi", "music", "3"),
            arrayOf("600", "apeach", "music", "2"),
        )
        val actual = Lessons42890().solution(relation)
        actual shouldBe 2
    }
})
