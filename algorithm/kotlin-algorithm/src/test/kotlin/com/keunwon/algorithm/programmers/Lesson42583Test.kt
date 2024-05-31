package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson42583Test : StringSpec({
    "case-1" {
        val bridge_length = 2
        val weight = 10
        val truck_weights = intArrayOf(7, 4, 5, 6)

        val actual = Lesson42583().solution(bridge_length, weight, truck_weights)

        actual shouldBe 8
    }

    "case-2" {
        val bridge_length = 100
        val weight = 100
        val truck_weights = intArrayOf(10)

        val actual = Lesson42583().solution(bridge_length, weight, truck_weights)

        actual shouldBe 101
    }

    "case-3" {
        val bridge_length = 100
        val weight = 100
        val truck_weights = intArrayOf(10, 10, 10, 10, 10, 10, 10, 10, 10, 10)

        val actual = Lesson42583().solution(bridge_length, weight, truck_weights)

        actual shouldBe 110
    }
})
