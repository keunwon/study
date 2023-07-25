package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons118669Test : StringSpec({
    "case_01" {
        val n = 6
        val paths = arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(2, 3, 5),
            intArrayOf(2, 4, 2),
            intArrayOf(2, 5, 4),
            intArrayOf(3, 4, 4),
            intArrayOf(4, 5, 3),
            intArrayOf(4, 6, 1),
            intArrayOf(5, 6, 1),
        )
        val gates = intArrayOf(1, 3)
        val summits = intArrayOf(5)

        val actual = Lessons118669().solution(n, paths, gates, summits)

        actual shouldBe intArrayOf(5, 3)
    }

    "case_02" {
        val n = 7
        val paths = arrayOf(
            intArrayOf(1, 4, 4),
            intArrayOf(1, 6, 1),
            intArrayOf(1, 7, 3),
            intArrayOf(2, 5, 2),
            intArrayOf(3, 7, 4),
            intArrayOf(5, 6, 6),
        )
        val gates = intArrayOf(1)
        val summits = intArrayOf(2, 3, 4)

        val actual = Lessons118669().solution(n, paths, gates, summits)

        actual shouldBe intArrayOf(3, 4)
    }

    "case_03" {
        val n = 7
        val paths = arrayOf(
            intArrayOf(1, 2, 5),
            intArrayOf(1, 4, 1),
            intArrayOf(2, 3, 1),
            intArrayOf(2, 6, 7),
            intArrayOf(4, 5, 1),
            intArrayOf(5, 6, 1),
            intArrayOf(6, 7, 1),
        )
        val gates = intArrayOf(3, 7)
        val summits = intArrayOf(1, 5)

        val actual = Lessons118669().solution(n, paths, gates, summits)

        actual shouldBe intArrayOf(5, 1)
    }

    "case_04" {
        val n = 5
        val paths = arrayOf(
            intArrayOf(1, 3, 10),
            intArrayOf(1, 4, 20),
            intArrayOf(2, 3, 4),
            intArrayOf(2, 4, 6),
            intArrayOf(3, 5, 20),
            intArrayOf(4, 5, 6),
        )
        val gates = intArrayOf(1, 2)
        val summits = intArrayOf(5)

        val actual = Lessons118669().solution(n, paths, gates, summits)

        actual shouldBe intArrayOf(5, 6)
    }
})
