package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem161990Test : StringSpec({
    "case_01" {
        val wallpaper = arrayOf(".#...", "..#..", "...#.")
        val actual = Problem161990().solution(wallpaper)
        actual shouldBe intArrayOf(0, 1, 3, 4)
    }

    "case_02" {
        val wallpaper = arrayOf("..........", ".....#....", "......##..", "...##.....", "....#.....")
        val actual = Problem161990().solution(wallpaper)
        actual shouldBe intArrayOf(1, 3, 5, 8)
    }

    "case_03" {
        val wallpaper =
            arrayOf(".##...##.", "#..#.#..#", "#...#...#", ".#.....#.", "..#...#..", "...#.#...", "....#....")
        val actual = Problem161990().solution(wallpaper)
        actual shouldBe intArrayOf(0, 0, 7, 9)
    }

    "case_04" {
        val wallpaper = arrayOf("..", "#.")
        val actual = Problem161990().solution(wallpaper)
        actual shouldBe intArrayOf(1, 0, 2, 1)
    }
})
