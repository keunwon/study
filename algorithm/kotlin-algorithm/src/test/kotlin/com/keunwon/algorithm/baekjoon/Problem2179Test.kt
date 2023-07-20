package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Problem2179Test : StringSpec({
    "case_01" {
        val words = listOf(
            "noon",
            "is",
            "lunch",
            "for",
            "most",
            "noone",
            "waits",
            "until",
            "two",
        )

        val actual = Problem2179().solution(words)

        actual shouldBe arrayOf("noon", "noone")
    }

    "case_02" {
        val words = listOf(
            "abcd",
            "abe",
            "abc",
            "abchldp",
        )

        val actual = Problem2179().solution(words)

        actual shouldBe arrayOf("abcd", "abc")
    }
})
