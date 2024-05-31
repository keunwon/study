package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson155651Test : StringSpec({
    "case-1" {
        val book_time = arrayOf(
            arrayOf("15:00", "17:00"),
            arrayOf("16:40", "18:20"),
            arrayOf("14:20", "15:20"),
            arrayOf("14:10", "19:20"),
            arrayOf("18:20", "21:20")
        )
        val actual = Lesson155651().solution(book_time)
        actual shouldBe 3
    }

    "case-2" {
        val book_time = arrayOf(
            arrayOf("09:10", "10:10"),
            arrayOf("10:20", "12:20")
        )
        val actual = Lesson155651().solution(book_time)
        actual shouldBe 1
    }

    "case-3" {
        val book_time = arrayOf(
            arrayOf("10:20", "12:30"),
            arrayOf("10:20", "12:30"),
            arrayOf("10:20", "12:30")
        )
        val actual = Lesson155651().solution(book_time)
        actual shouldBe 3
    }
})