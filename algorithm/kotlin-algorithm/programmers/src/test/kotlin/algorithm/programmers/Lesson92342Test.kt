package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson92342Test : StringSpec({
    "case-1" {
        val n = 5
        val info = intArrayOf(2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0)

        val actual = Lesson92342().solution(n, info)

        actual shouldBe intArrayOf(0, 2, 2, 0, 1, 0, 0, 0, 0, 0, 0)
    }

    "case-2" {
        val n = 1
        val info = intArrayOf(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

        val actual = Lesson92342().solution(n, info)

        actual shouldBe intArrayOf(-1)
    }

    "case-3" {
        val n = 9
        val info = intArrayOf(0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1)

        val actual = Lesson92342().solution(n, info)

        actual shouldBe intArrayOf(1, 1, 2, 0, 1, 2, 2, 0, 0, 0, 0)
    }

    "case-4" {
        val n = 10
        val info = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3)

        val actual = Lesson92342().solution(n, info)

        actual shouldBe intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 2)
    }
})
