package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem13460Test : StringSpec({
    "case-1" {
        val n = 5
        val m = 5
        val board = arrayOf(
            "#####".toCharArray(),
            "#..B#".toCharArray(),
            "#.#.#".toCharArray(),
            "#RO.#".toCharArray(),
            "#####".toCharArray(),
        )

        val actual = Problem13460().solution(n, m, board)

        actual shouldBe 1
    }

    "case-2" {
        val n = 7
        val m = 7
        val board = arrayOf(
            "#######".toCharArray(),
            "#...RB#".toCharArray(),
            "#.#####".toCharArray(),
            "#.....#".toCharArray(),
            "#####.#".toCharArray(),
            "#O....#".toCharArray(),
            "#######".toCharArray(),
        )

        val actual = Problem13460().solution(n, m, board)

        actual shouldBe 5
    }

    "case-3" {
        val n = 7
        val m = 7
        val board = arrayOf(
            "#######".toCharArray(),
            "#..R#B#".toCharArray(),
            "#.#####".toCharArray(),
            "#.....#".toCharArray(),
            "#####.#".toCharArray(),
            "#O....#".toCharArray(),
            "#######".toCharArray(),
        )

        val actual = Problem13460().solution(n, m, board)

        actual shouldBe 5
    }

    "case-4" {
        val n = 10
        val m = 10
        val board = arrayOf(
            "##########".toCharArray(),
            "#R#...##B#".toCharArray(),
            "#...#.##.#".toCharArray(),
            "#####.##.#".toCharArray(),
            "#......#.#".toCharArray(),
            "#.######.#".toCharArray(),
            "#.#....#.#".toCharArray(),
            "#.#.#.#..#".toCharArray(),
            "#...#.O#.#".toCharArray(),
            "##########".toCharArray(),
        )

        val actual = Problem13460().solution(n, m, board)

        actual shouldBe -1
    }

    "case-5" {
        val n = 3
        val m = 7
        val board = arrayOf(
            "#######".toCharArray(),
            "#R.O.B#".toCharArray(),
            "#######".toCharArray(),
        )

        val actual = Problem13460().solution(n, m, board)

        actual shouldBe 1
    }

    "case-6" {
        val n = 10
        val m = 10
        val board = arrayOf(
            "##########".toCharArray(),
            "#R#...##B#".toCharArray(),
            "#...#.##.#".toCharArray(),
            "#####.##.#".toCharArray(),
            "#......#.#".toCharArray(),
            "#.######.#".toCharArray(),
            "#.#....#.#".toCharArray(),
            "#.#.##...#".toCharArray(),
            "#O..#....#".toCharArray(),
            "##########".toCharArray(),
        )

        val actual = Problem13460().solution(n, m, board)

        actual shouldBe 7
    }

    "case-7" {
        val n = 3
        val m = 10
        val board = arrayOf(
            "##########".toCharArray(),
            "#.O....RB#".toCharArray(),
            "##########".toCharArray(),
        )

        val actual = Problem13460().solution(n, m, board)

        actual shouldBe -1
    }
})
