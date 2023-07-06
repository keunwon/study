package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons42578Test : StringSpec({
    "case_01" {
        val clothes = arrayOf(
            arrayOf("yellow_hat", "headgear"),
            arrayOf("blue_sunglasses", "eyewear"),
            arrayOf("green_turban", "headgear"),
        )

        val actual = Lessons42578().solution(clothes)

        actual shouldBe 5
    }

    "case_02" {
        val clothes = arrayOf(
            arrayOf("crow_mask", "face"),
            arrayOf("blue_sunglasses", "face"),
            arrayOf("smoky_makeup", "face"),
        )

        val actual = Lessons42578().solution(clothes)

        actual shouldBe 3
    }
})
