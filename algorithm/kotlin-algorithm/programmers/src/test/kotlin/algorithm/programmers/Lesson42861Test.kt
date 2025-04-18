package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson42861Test : StringSpec({
    "case-1" {
        val n = 4
        val costs = arrayOf(
            intArrayOf(0, 1, 1),
            intArrayOf(0, 2, 2),
            intArrayOf(1, 2, 5),
            intArrayOf(1, 3, 1),
            intArrayOf(2, 3, 8)
        )

        val actual = Lesson42861().solution(n, costs)

        actual shouldBe 4
    }
})
