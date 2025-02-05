package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson1836Test : StringSpec({
    "case-1" {
        val m = 3
        val n = 3
        val board = arrayOf("DBA", "C*A", "CDB")

        val actual = Lesson1836().solution(m, n, board)

        actual shouldBe "ABCD"
    }

    "case-2" {
        val m = 2
        val n = 4
        val board = arrayOf("NRYN", "ARYA")

        val actual = Lesson1836().solution(m, n, board)

        actual shouldBe "RYAN"
    }

    "caes-3" {
        val m = 4
        val n = 4
        val board = arrayOf(".ZI.", "M.**", "MZU.", ".IU.")

        val actual = Lesson1836().solution(m, n, board)

        actual shouldBe "MUZI"
    }

    "case-4" {
        val m = 2
        val n = 2
        val board = arrayOf("AB", "BA")

        val actual = Lesson1836().solution(m, n, board)

        actual shouldBe "IMPOSSIBLE"
    }
})
