package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson42747Test : StringSpec({
    "case-1" {
        val actual = Lesson42747().solution(
            citations = intArrayOf(3, 0, 6, 1, 5)
        )
        actual shouldBe 3
    }
})
