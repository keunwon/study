package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem16235Test : StringSpec({
    "case-1" {
        val k = 1
        val board = arrayOf(intArrayOf(1))
        val treeInfos = arrayOf(intArrayOf(1, 1, 1))

        val actual = Problem16235().solution(k, board, treeInfos)

        actual shouldBe 1
    }

    "case-2" {
        val k = 4
        val board = arrayOf(intArrayOf(1))
        val treeInfos = arrayOf(intArrayOf(1, 1, 1))

        val actual = Problem16235().solution(k, board, treeInfos)

        actual shouldBe 0
    }

    "case-3" {
        val k = 1
        val board = arrayOf(
            intArrayOf(2, 3, 2, 3, 2),
            intArrayOf(2, 3, 2, 3, 2),
            intArrayOf(2, 3, 2, 3, 2),
            intArrayOf(2, 3, 2, 3, 2),
            intArrayOf(2, 3, 2, 3, 2),
        )
        val treeInfos = arrayOf(
            intArrayOf(2, 1, 3),
            intArrayOf(3, 2, 3),
        )

        val actual = Problem16235().solution(k, board, treeInfos)

        actual shouldBe 2
    }

    "case-4" {
        val k = 2
        val board = arrayOf(
            intArrayOf(2, 3, 2, 3, 2),
            intArrayOf(2, 3, 2, 3, 2),
            intArrayOf(2, 3, 2, 3, 2),
            intArrayOf(2, 3, 2, 3, 2),
            intArrayOf(2, 3, 2, 3, 2),
        )
        val treeInfos = arrayOf(
            intArrayOf(2, 1, 3),
            intArrayOf(3, 2, 3),
        )

        val actual = Problem16235().solution(k, board, treeInfos)

        actual shouldBe 15
    }

    "case-5" {
        val k = 3
        val board = arrayOf(
            intArrayOf(2, 3, 2, 3, 2),
            intArrayOf(2, 3, 2, 3, 2),
            intArrayOf(2, 3, 2, 3, 2),
            intArrayOf(2, 3, 2, 3, 2),
            intArrayOf(2, 3, 2, 3, 2),
        )
        val treeInfos = arrayOf(
            intArrayOf(2, 1, 3),
            intArrayOf(3, 2, 3),
        )

        val actual = Problem16235().solution(k, board, treeInfos)

        actual shouldBe 13
    }

    "case-6" {
        val k = 4
        val board = arrayOf(
            intArrayOf(2, 3, 2, 3, 2),
            intArrayOf(2, 3, 2, 3, 2),
            intArrayOf(2, 3, 2, 3, 2),
            intArrayOf(2, 3, 2, 3, 2),
            intArrayOf(2, 3, 2, 3, 2),
        )
        val treeInfos = arrayOf(
            intArrayOf(2, 1, 3),
            intArrayOf(3, 2, 3),
        )

        val actual = Problem16235().solution(k, board, treeInfos)

        actual shouldBe 13
    }

    "case-7" {
        val k = 5
        val board = arrayOf(
            intArrayOf(2, 3, 2, 3, 2),
            intArrayOf(2, 3, 2, 3, 2),
            intArrayOf(2, 3, 2, 3, 2),
            intArrayOf(2, 3, 2, 3, 2),
            intArrayOf(2, 3, 2, 3, 2),
        )
        val treeInfos = arrayOf(
            intArrayOf(2, 1, 3),
            intArrayOf(3, 2, 3),
        )

        val actual = Problem16235().solution(k, board, treeInfos)

        actual shouldBe 13
    }

    "case-8" {
        val k = 6
        val board = arrayOf(
            intArrayOf(2, 3, 2, 3, 2),
            intArrayOf(2, 3, 2, 3, 2),
            intArrayOf(2, 3, 2, 3, 2),
            intArrayOf(2, 3, 2, 3, 2),
            intArrayOf(2, 3, 2, 3, 2),
        )
        val treeInfos = arrayOf(
            intArrayOf(2, 1, 3),
            intArrayOf(3, 2, 3),
        )

        val actual = Problem16235().solution(k, board, treeInfos)

        actual shouldBe 85
    }
})
