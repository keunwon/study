package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem13458Test : StringSpec({
    "case-1" {
        val students = intArrayOf(1)
        val b = 1
        val c = 1

        val actual = Problem13458().solution(students, b, c)

        actual shouldBe 1L
    }

    "case-2" {
        val students = intArrayOf(3, 4, 5)
        val b = 2
        val c = 2

        val actual = Problem13458().solution(students, b, c)

        actual shouldBe 7L
    }
})
