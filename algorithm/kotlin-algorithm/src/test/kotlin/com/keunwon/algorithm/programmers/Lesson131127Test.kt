package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson131127Test : StringSpec({
    "case-1" {
        val wants = arrayOf("banana", "apple", "rice", "pork", "pot")
        val numbers = intArrayOf(3, 2, 2, 2, 1)
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
            "banana"
        )

        val actual = Lesson131127().solution(wants, numbers, discount)

        actual shouldBe 3
    }

    "case-2" {
        val wants = arrayOf("apple")
        val numbers = intArrayOf(10)
        val discount =
            arrayOf("banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana")

        val actual = Lesson131127().solution(wants, numbers, discount)

        actual shouldBe 0
    }
})
