package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem2206Test : StringSpec({
    "case_01" {
        val map = arrayOf(
            "0100",
            "1110",
            "1000",
            "0000",
            "0111",
            "0000",
        ).map { line -> line.map { it.digitToInt() }.toIntArray() }.toTypedArray()

        val actual = Problem2206().solution(map)

        actual shouldBe 15
    }

    "case_02" {
        val map = arrayOf(
            "0111",
            "1111",
            "1111",
            "1110",
        ).map { line -> line.map { it.digitToInt() }.toIntArray() }.toTypedArray()

        val actual = Problem2206().solution(map)

        actual shouldBe -1
    }
})
