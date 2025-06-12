package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson132267Test : StringSpec({
    "case-1" {
        val actual = Lesson132267().solution(
            a = 2,
            b = 1,
            n = 20,
        )
        actual shouldBe 19
    }

    "case-2" {
        val actual = Lesson132267().solution(
            a = 3,
            b = 1,
            n = 20,
        )
        actual shouldBe 9
    }
})
