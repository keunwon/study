package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson81303Test : StringSpec({
    "case-1" {
        val n = 8
        val k = 2
        val cmd = arrayOf("D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z")

        val actual = Lesson81303().solution(n, k, cmd)

        actual shouldBe "OOOOXOOO"
    }

    "case-2" {
        val n = 8
        val k = 2
        val cmd = arrayOf("D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C")

        val actula = Lesson81303().solution(n, k, cmd)

        actula shouldBe "OOXOXOOO"
    }
})