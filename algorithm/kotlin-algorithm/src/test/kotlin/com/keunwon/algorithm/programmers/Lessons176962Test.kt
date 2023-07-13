package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons176962Test : StringSpec({
    "case_01" {
        val plans = arrayOf(
            arrayOf("korean", "11:40", "30"),
            arrayOf("english", "12:10", "20"),
            arrayOf("math", "12:30", "40"),
        )

        val actual = Lessons176962().solution(plans)

        actual shouldBe arrayOf("korean", "english", "math")
    }

    "case_02" {
        val plans = arrayOf(
            arrayOf("science", "12:40", "50"),
            arrayOf("music", "12:20", "40"),
            arrayOf("history", "14:00", "30"),
            arrayOf("computer", "12:30", "100"),
        )

        val actual = Lessons176962().solution(plans)

        actual shouldBe arrayOf("science", "history", "computer", "music")
    }

    "case_03" {
        val plans = arrayOf(
            arrayOf("aaa", "12:00", "20"),
            arrayOf("bbb", "12:10", "30"),
            arrayOf("ccc", "12:40", "10"),
        )

        val actual = Lessons176962().solution(plans)

        actual shouldBe arrayOf("bbb", "ccc", "aaa")
    }
})
