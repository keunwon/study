package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson138475Test : StringSpec({
    "case-1" {
        val e = 8
        val starts = intArrayOf(1, 3, 7)

        val actual = Lesson138475().solution(e, starts)

        actual shouldBe intArrayOf(6, 6, 8)
    }
})
