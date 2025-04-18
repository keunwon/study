package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson86491Test : StringSpec({
    "case-1" {
        val sizes = arrayOf(
            intArrayOf(60, 50),
            intArrayOf(30, 70),
            intArrayOf(60, 30),
            intArrayOf(80, 40),
        )
        val actual = Lesson86491().solution(sizes)
        actual shouldBe 4000
    }

    "case-2" {
        val sizes = arrayOf(
            intArrayOf(10, 7),
            intArrayOf(12, 3),
            intArrayOf(8, 15),
            intArrayOf(14, 7),
            intArrayOf(5, 15),
        )
        val actual = Lesson86491().solution(sizes)
        actual shouldBe 120
    }

    "case-3" {
        val sizes = arrayOf(
            intArrayOf(14, 4),
            intArrayOf(19, 6),
            intArrayOf(6, 16),
            intArrayOf(18, 7),
            intArrayOf(7, 11),
        )
        val actual = Lesson86491().solution(sizes)
        actual shouldBe 133
    }
})
