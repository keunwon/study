package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson172928Test : StringSpec({
    "case-1" {
        val park = arrayOf("SOO", "OOO", "OOO")
        val routes = arrayOf("E 2", "S 2", "W 1")

        val actual = Lesson172928().solution(park, routes)

        actual shouldBe intArrayOf(2, 1)
    }

    "case-2" {
        val park = arrayOf("SOO", "OXX", "OOO")
        val routes = arrayOf("E 2", "S 2", "W 1")

        val actual = Lesson172928().solution(park, routes)

        actual shouldBe intArrayOf(0, 1)
    }

    "case-3" {
        val park = arrayOf("OSO", "OOO", "OXO", "OOO")
        val routes = arrayOf("E 2", "S 3", "W 1")

        val actual = Lesson172928().solution(park, routes)

        actual shouldBe intArrayOf(0, 0)
    }
})
