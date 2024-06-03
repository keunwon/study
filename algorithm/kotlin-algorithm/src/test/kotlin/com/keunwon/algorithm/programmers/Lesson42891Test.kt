package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson42891Test : StringSpec({
    "case-1" {
        val food_times = intArrayOf(3, 1, 2)
        val k = 5L

        val actual = Lesson42891().solution(food_times, k)

        actual shouldBe 1
    }
})