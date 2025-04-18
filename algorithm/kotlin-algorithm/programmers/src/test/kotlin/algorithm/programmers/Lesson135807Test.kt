package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson135807Test : StringSpec({
    "case-1" {
        val arrayA = intArrayOf(10, 17)
        val arrayB = intArrayOf(5, 20)

        val actual = Lesson135807().solution(arrayA, arrayB)

        actual shouldBe 0
    }

    "case-2" {
        val arrayA = intArrayOf(10, 20)
        val arrayB = intArrayOf(5, 17)

        val actual = Lesson135807().solution(arrayA, arrayB)

        actual shouldBe 10
    }

    "case-3" {
        val arrayA = intArrayOf(14, 35, 119)
        val arrayB = intArrayOf(18, 30, 102)

        val actual = Lesson135807().solution(arrayA, arrayB)

        actual shouldBe 7
    }
})
