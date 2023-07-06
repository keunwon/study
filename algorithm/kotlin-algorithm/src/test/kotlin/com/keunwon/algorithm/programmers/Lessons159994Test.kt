package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons159994Test : StringSpec({
    "case_01" {
        val cards1 = arrayOf("i", "drink", "water")
        val cards2 = arrayOf("want", "to")
        val goal = arrayOf("i", "want", "to", "drink", "water")

        val actual = Lessons159994().solution(cards1, cards2, goal)

        actual shouldBe "Yes"
    }

    "case_02" {
        val cards1 = arrayOf("i", "water", "drink")
        val cards2 = arrayOf("i", "drink", "water")
        val goal = arrayOf("i", "want", "to", "drink", "water")

        val actual = Lessons159994().solution(cards1, cards2, goal)

        actual shouldBe "No"
    }
})
