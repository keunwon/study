package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson214288Test : StringSpec({
    "case-1" {
        val k = 3
        val n = 5
        val regs = arrayOf(
            intArrayOf(10, 60, 1),
            intArrayOf(15, 100, 3),
            intArrayOf(20, 30, 1),
            intArrayOf(30, 50, 3),
            intArrayOf(50, 40, 1),
            intArrayOf(60, 30, 2),
            intArrayOf(65, 30, 1),
            intArrayOf(70, 100, 2),
        )

        val actual = Lesson214288().solution(k, n, regs)

        actual shouldBe 25
    }

    "case-2" {
        val k = 2
        val n = 3
        val regs = arrayOf(
            intArrayOf(5, 55, 2),
            intArrayOf(10, 90, 2),
            intArrayOf(20, 40, 2),
            intArrayOf(50, 45, 2),
            intArrayOf(100, 50, 2)
        )

        val actual = Lesson214288().solution(k, n, regs)

        actual shouldBe 90
    }
})
