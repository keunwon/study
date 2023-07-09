package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons155651Test : StringSpec({
    "case_01" {
        val bookTime = arrayOf(
            arrayOf("15:00", "17:00"),
            arrayOf("16:40", "18:20"),
            arrayOf("14:20", "15:20"),
            arrayOf("14:10", "19:20"),
            arrayOf("18:20", "21:20"),
        )

        val actual = Lessons155651().solution(bookTime)

        actual shouldBe 3
    }

    "case_02" {
        val bookTime = arrayOf(
            arrayOf("09:10", "10:10"),
            arrayOf("10:20", "12:20"),
        )

        val actual = Lessons155651().solution(bookTime)

        actual shouldBe 1
    }

    "case_03" {
        val bookTime = arrayOf(
            arrayOf("10:20", "12:30"),
            arrayOf("10:20", "12:30"),
            arrayOf("10:20", "12:30"),
        )

        val actual = Lessons155651().solution(bookTime)

        actual shouldBe 3
    }
})
