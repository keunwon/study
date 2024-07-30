package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson150370Test : StringSpec({
    "case-1" {
        val today = "2022.05.19"
        val terms = arrayOf("A 6", "B 12", "C 3")
        val privacies = arrayOf("2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C")

        val actual = Lesson150370().solution(today, terms, privacies)

        actual shouldBe intArrayOf(1, 3)
    }

    "case-2" {
        val today = "2020.01.01"
        val terms = arrayOf("Z 3", "D 5")
        val privacies = arrayOf("2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z")

        val actual = Lesson150370().solution(today, terms, privacies)

        actual shouldBe intArrayOf(1, 4, 5)
    }
})
