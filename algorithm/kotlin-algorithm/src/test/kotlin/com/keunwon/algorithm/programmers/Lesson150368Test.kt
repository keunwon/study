package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson150368Test : StringSpec({
    "case-1" {
        val users = arrayOf(
            intArrayOf(40, 10000),
            intArrayOf(25, 10000)
        )
        val emoticons = intArrayOf(7000, 9000)

        val actual = Lesson150368().solution(users, emoticons)

        actual shouldBe intArrayOf(1, 5400)
    }

    "case-2" {
        val users = arrayOf(
            intArrayOf(40, 2900),
            intArrayOf(23, 10000),
            intArrayOf(11, 5200),
            intArrayOf(5, 5900),
            intArrayOf(40, 3100),
            intArrayOf(27, 9200),
            intArrayOf(32, 6900)
        )
        val emoticons = intArrayOf(1300, 1500, 1600, 4900)

        val actual = Lesson150368().solution(users, emoticons)

        actual shouldBe intArrayOf(4, 13860)
    }
})