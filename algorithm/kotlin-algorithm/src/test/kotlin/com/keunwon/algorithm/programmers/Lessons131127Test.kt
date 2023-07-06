package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons131127Test : StringSpec({
    "case_01" {
        val want = arrayOf("banana", "apple", "rice", "pork", "pot")
        val number = intArrayOf(3, 2, 2, 2, 1)
        val discount = arrayOf(
            "chicken",
            "apple",
            "apple",
            "banana",
            "rice",
            "apple",
            "pork",
            "banana",
            "pork",
            "rice",
            "pot",
            "banana",
            "apple",
            "banana",
        )

        val actual = Lessons131127().solution(want, number, discount)

        actual shouldBe 3
    }

    "case_02" {
        val want = arrayOf("apple")
        val number = intArrayOf(10)
        val discount = arrayOf(
            "banana",
            "banana",
            "banana",
            "banana",
            "banana",
            "banana",
            "banana",
            "banana",
            "banana",
            "banana",
        )

        val actual = Lessons131127().solution(want, number, discount)

        actual shouldBe 0
    }
})
